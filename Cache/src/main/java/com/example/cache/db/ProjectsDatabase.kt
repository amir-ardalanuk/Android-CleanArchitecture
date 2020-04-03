package com.example.cache.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.dao.CachedProjectDao
import com.example.cache.dao.ConfigDao
import com.example.cache.model.CachedProject
import com.example.cache.model.Config
import com.example.cache.model.SingletonHolder

@Database(entities = arrayOf(
    CachedProject::class,
    Config::class)
    ,version = 1,
    exportSchema = false)
abstract class ProjectsDatabase : RoomDatabase() {

    abstract fun cacheProjectDao(): CachedProjectDao
    abstract fun configDao(): ConfigDao

    companion object : SingletonHolder<Context, ProjectsDatabase>({
        Room.databaseBuilder(
            it.applicationContext,
            ProjectsDatabase::class.java,
            "projects.db"
        ).build()
    })

}