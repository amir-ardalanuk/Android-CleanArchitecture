package com.example.presentation.state

class Resource<out T> constructor(val state : ResourseState ,
                                  val data : T? ,
                                  val message : String?)