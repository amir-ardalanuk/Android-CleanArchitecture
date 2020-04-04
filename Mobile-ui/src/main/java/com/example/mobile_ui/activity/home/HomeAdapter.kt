package com.example.mobile_ui.activity.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobile_ui.R
import com.example.mobile_ui.model.Project
import kotlinx.android.synthetic.main.item_home_rec.view.*
import javax.inject.Inject

class HomeAdapter  @Inject constructor() : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    var projects : List<Project> = arrayListOf();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_rec,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = projects.size

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val project = projects[position]
        holder.mainTextView.text = project.name
        holder.dateCreatedTv.text = project.dateCreate
        holder.starCountTv.text = project.starCount
        Glide.with(holder.avatarImgV).load(project.ownerAvatar ?: "").into(holder.avatarImgV)
    }



    fun submitList(list: List<Project>){
        this.projects = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mainTextView : AppCompatTextView
        var starCountTv: AppCompatTextView
        var dateCreatedTv: AppCompatTextView
        var avatarImgV: AppCompatImageView

        init {
            mainTextView = view.item_home_main_tv;
            starCountTv = view.item_home_start_count_tv
            dateCreatedTv = view.item_home_date_tv
            avatarImgV = view.item_home_avatar
        }
    }
}