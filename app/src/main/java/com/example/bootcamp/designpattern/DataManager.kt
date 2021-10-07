package com.example.bootcamp.designpattern

import kotlin.random.Random

//In Kotlin, there is no need for using private construction or static method to create a singleton class
object DataManager {

    init {
        println("Singleton class invoked.")
    }

    fun getUserData(): Data {
        return Data("sara ${Random(10).nextInt()}")
    }
}