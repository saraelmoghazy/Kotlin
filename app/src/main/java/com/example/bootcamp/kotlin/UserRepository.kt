package com.example.bootcamp.kotlin

import com.example.bootcamp.network.RetrofitProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.URL


class UserRepository() {

    // Function that makes the network request, blocking the current thread
    suspend fun addUser(
        userName: String,
        age: Int
    ): Response<Void> {
        val service = RetrofitProvider.getInstance().create(UserAPI::class.java)
        // You should always use withContext() inside a suspend function when you need main-safety,
        // such as when reading from or writing to disk, performing network operations,
        // or running CPU-intensive operations.

        return service.addUser(UserRequest(userName, age))
//        withContext(Dispatchers.IO) {
//            // block of code running in background
//        }
    }

    companion object {
        private const val loginUrl = "https://example.com/login"
    }
}
