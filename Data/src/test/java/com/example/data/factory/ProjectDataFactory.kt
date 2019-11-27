package com.example.data.factory

import amir.ardalani.domain.model.Project
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object ProjectDataFactory {

    fun randomUuid():String {
        return  UUID.randomUUID().toString()
    }

    fun randomBoolean():Boolean {
        return Math.random() < 0.5
    }

    fun randomInt():Int {
        return ThreadLocalRandom.current().nextInt(0,1000 + 1)
    }

    fun randomLong():Long {
        return  randomInt().toLong()
    }

    fun makeProject(): Project {
        return Project(randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomBoolean())
    }

    fun makeProjectList(count: Int):List<Project>{
        var projects = mutableListOf<Project>()
        repeat(count){
            projects.add(makeProject())
        }
        return  projects
    }
}