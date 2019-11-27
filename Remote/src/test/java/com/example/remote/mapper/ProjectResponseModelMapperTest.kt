package com.example.remote.mapper

import com.example.data.model.ProjectEntity
import com.example.remote.factory.ProjectDataFactory
import com.example.remote.model.ProjectModel
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ProjectResponseModelMapperTest  {

    val mapper = ProjectResponseModelMapper()

    @Test
    fun testMapperDataToEntity(){
        val model  = ProjectDataFactory.makeProject()
        val entity = mapper.mapFromModel(model)

        checkEntityAndModelEquallity(model,entity)
    }

    fun checkEntityAndModelEquallity(model : ProjectModel,entity : ProjectEntity){
        assertEquals(model.id,entity.id)
        assertEquals(model.name,entity.name)
        assertEquals(model.fullname,entity.fullname)
        assertEquals(model.starCount.toString(),entity.starCount)
        assertEquals(model.dateCreate,entity.dateCreated)
        assertEquals(model.owner?.ownerName,entity.ownerName)
        assertEquals(model.owner?.ownerAvatar,entity.ownerAvater)
    }
}