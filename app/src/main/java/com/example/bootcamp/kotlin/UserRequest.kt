package com.example.bootcamp.kotlin

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @field:SerializedName("username")
    val username: String? = null,
    @field:SerializedName("age")
    val age: Int? = null


)
