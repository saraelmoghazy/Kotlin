package com.example.bootcamp.kotlin

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("addUser")
    suspend fun addUser(@Body userRequest: UserRequest): Response<Void>


}