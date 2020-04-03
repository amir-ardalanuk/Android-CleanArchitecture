package com.example.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.db.ProjectConstants.QUERY_PROJECTS
import com.example.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECT
import com.example.cache.db.ProjectConstants.DELETE_PROJECTS
import com.example.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARKED_STATUS
import com.example.cache.db.ProjectConstants.QUERY_EXISTS
import com.example.cache.model.CachedProject
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class CachedProjectDao {

    @Query(QUERY_EXISTS)
    abstract fun cachedProjectsExist(): Single<Int>

    @Query(QUERY_PROJECTS)
    abstract fun getProjects():Flowable<List<CachedProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProject(projects : List<CachedProject>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECT)
    abstract fun getBookmarkedProjects():Flowable<List<CachedProject>>

    @Query(QUERY_UPDATE_BOOKMARKED_STATUS)
    abstract fun setBookmarkStatus(isBookmarked:Boolean,projectId : String)
}