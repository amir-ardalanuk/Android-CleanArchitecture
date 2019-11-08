package amir.ardalani.domain.model

data class Project(val id : String,  val name : String , val fullname : String,
                   val starCount : String , val dateCreated : String,
                   val ownerName : String , val ownerAvater : String ,
                   val isBookmarked : Boolean)