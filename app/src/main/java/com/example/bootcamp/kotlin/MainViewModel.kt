package com.example.bootcamp.kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcamp.network.APIError
import com.example.bootcamp.network.APIException
import com.example.bootcamp.network.ErrorType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    val successResult = MutableLiveData<Boolean>()

    val errorLiveData = MutableLiveData<APIException>()

    fun addUser(username: String, age: Int) {
        //lanuch -> fire and forget
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val result = userRepository.addUser(username, age)

                when (result.isSuccessful) {
                    true -> {
                        successResult.value = true
                    }
                    else -> {
                        onResponseFail(result)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorLiveData.value =
                        APIException("No internet connection", ErrorType.NETWORK)
                }
            }
        }
    }

    private fun <M> onResponseFail(result: Response<M>) {
        val gson = Gson()
        val type = object : TypeToken<APIError>() {}.type
        val errorResponse: APIError? = gson.fromJson(result.errorBody()!!.charStream(), type)
        errorLiveData.value = APIException(errorResponse?.errorMessage, ErrorType.HTTP)
    }
}