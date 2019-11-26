package com.example.data.factory.mapper

import amir.ardalani.domain.model.Project
import com.example.data.factory.ProjectFactory
import com.example.data.mapper.ProjectMapper
import com.example.data.model.ProjectEntity
import org.junit.Test
import kotlin.test.assertEquals

class ProjectMapperTest {

    private val mappper = ProjectMapper()

    @Test
    fun mapFromEntityMapsData(){
        val entity = ProjectFactory.makeProjectEntity()
        val model = mappper.mapFromEntity(entity)
        assertEqualData(entity,model)
    }

    @Test
    fun mapFromModeltoData(){
        val model = ProjectFactory.makeProject()
        val entity = mappper.mapToEntity(model)
        assertEqualData(entity,model)
    }

    private fun assertEqualData(entity:ProjectEntity,
                                model : Project){
        assertEquals(entity.id,model.id)
        assertEquals(entity.name,model.name)
        assertEquals(entity.fullname,model.fullname)
        assertEquals(entity.dateCreated,model.dateCreated)
        assertEquals(entity.starCount,model.starCount)
        assertEquals(entity.ownerAvater,model.ownerAvater)
        assertEquals(entity.ownerName,model.ownerName)
        assertEquals(entity.isBookmarked,model.isBookmarked)


    }
}