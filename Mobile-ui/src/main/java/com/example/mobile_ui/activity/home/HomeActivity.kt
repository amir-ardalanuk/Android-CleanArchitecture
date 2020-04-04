package com.example.mobile_ui.activity.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_ui.R
import com.example.mobile_ui.injection.ViewModelFactory
import com.example.mobile_ui.mapper.ProjectViewMapper
import com.example.mobile_ui.model.Project
import com.example.presentation.BrowseProjectViewModel
import com.example.presentation.model.ProjectViewModel
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourseState
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    @Inject lateinit var mapper: ProjectViewMapper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: BrowseProjectViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(BrowseProjectViewModel::class.java)
        setupHomeViews()
        setupHomeRecycler()

        viewModel.getProject().observe(this,dataStateObserver)
        viewModel.fetchProjects()
    }

    private fun setupHomeViews(){
        recyclerView = findViewById(R.id.home_recyclerView)
        progress = findViewById(R.id.home_progressbar)
    }


    private fun setupScreenForSuccess(projects: List<Project>?) {
        if (progress.isVisible) {
            progress.isVisible = false
        }
        projects?.let { list -> (recyclerView.adapter as? HomeAdapter)?.submitList(list) }
    }

    private fun setupHomeRecycler() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = HomeAdapter()
        }
    }

    private val dataStateObserver = Observer<Resource<List<ProjectViewModel>>> { resource ->
        when (resource.state) {
            ResourseState.SUCCESS -> {
                setupScreenForSuccess(resource.data?.map { mapper.mapToView(it) })
            }
            ResourseState.LOADING -> {
                if (recyclerView.adapter?.itemCount ?: 0 == 0 ) {
                    progress.isVisible = true
                }
            }
            ResourseState.ERROR -> {
                resource.message?.let { Timber.e(it) }
                progress.isVisible = false
                findViewById<View?>(android.R.id.content)?.let {
                    Snackbar.make(it, resource.message ?: "", Snackbar.LENGTH_INDEFINITE).show()
                }
            }
        }
    }

}
