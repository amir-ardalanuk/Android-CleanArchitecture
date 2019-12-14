package com.example.cache.model

import androidx.room.Entity
import com.example.cache.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(val lastCacheTime:Long){

}