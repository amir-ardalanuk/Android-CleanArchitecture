package com.example.remote.model

import com.google.gson.annotations.SerializedName

open
class ProjectModel (val id :String,
                    val name : String?,
                    @SerializedName("full_name") val fullname :String?,
                    @SerializedName("stargazers_count") val starCount : Int?,
                    @SerializedName("created_at") val dateCreate : String?,
                    val owner:OwnerModel?
                    )