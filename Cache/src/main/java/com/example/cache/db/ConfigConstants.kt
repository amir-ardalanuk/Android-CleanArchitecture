package com.example.cache.db

object ConfigConstants{

    const val TABLE_NAME = "configs"
    const val COLUMN_IS_BOOKMARKED = "is_bookmarked"
    const val COLUMN_PROJECT_ID = "project_id"

    const val QUERY_CONFIG = "SELECT * FROM $TABLE_NAME"
    const val DELETE_PROJECTS = "DELETE * FROM $TABLE_NAME"
    const val QUERY_BOOKMARKED_PROJECT = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_IS_BOOKMARKED = 1"
    const val QUERY_UPDATE_BOOKMARKED_STATUS = "UPDATE $TABLE_NAME SET :isBookmarked WHERE $COLUMN_PROJECT_ID = :projectId"
}