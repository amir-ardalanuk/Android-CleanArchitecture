package com.example.cache.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache.dao.CachedProjectDao
import com.example.cache.dao.ConfigDao
import com.example.cache.model.CachedProject
import com.example.cache.model.Config
import javax.inject.Inject

@Database(entities = arrayOf(CachedProject::class,
    Config::class),version = 1)
abstract class ProjectsDatabase @Inject constructor() : RoomDatabase()  {

    abstract fun cacheProjectDao():CachedProjectDao
    abstract fun configDao():ConfigDao



    companion object  {

    private var INSTANCE :ProjectsDatabase? = null
    private var lock  = Any()

    fun getInstance(context:Context):ProjectsDatabase {
        if (INSTANCE == null) {
            synchronized(lock){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ProjectsDatabase::class.java ,"projects.db").build()
                }
                return  INSTANCE as ProjectsDatabase
            }
        }
        return  INSTANCE as ProjectsDatabase
    }
    }
}