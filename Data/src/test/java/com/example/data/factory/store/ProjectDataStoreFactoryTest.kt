package com.example.data.factory.store

import com.example.data.store.ProjectCacheDataStore
import com.example.data.store.ProjectDataStoreFactory
import com.example.data.store.ProjectRemoteDataStore
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertEquals

class ProjectDataStoreFactoryTest {
    private val cacheStore = mock<ProjectCacheDataStore>()
    private val remoteStore = mock<ProjectRemoteDataStore>()
    private val facotry = ProjectDataStoreFactory(cacheStore,remoteStore)


    @Test
    fun getDataStoreRetursRemoteWhenCacheExpired(){
        assertEquals(remoteStore, facotry.getDataStore(true,true))
    }

    @Test
    fun getDataStoreRetursRemoteWhenProjectNotCache(){
        assertEquals(remoteStore, facotry.getDataStore(false,false))
    }


    @Test
    fun getDataStoreRetursCache(){
        assertEquals(cacheStore, facotry.getDataStore(true,false))
    }


    @Test
    fun getCacheStore(){
        assertEquals(cacheStore, facotry.getCacheDataStore())
    }


}