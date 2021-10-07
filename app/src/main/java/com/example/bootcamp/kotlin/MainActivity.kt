package com.example.bootcamp.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.bootcamp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var number: Int? = null
    var count: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        count!!.inc()

        when (count) {
            1, 2, 3 -> println("branch 1")
            in 3..6 -> println("branch 5")
            else -> println("Branch 3")
        }


        val aaa = 10 / 0
        var str = "example"
        when (str) {
            is String -> println("right")
        }

        println(generateNewString(count!!))

        // Lambda expressions / Anonymous functions -> function doesnt have name , can be passed as expression
        val sum = { x: Int, y: Int -> x + y }
        val lambdaExample = { "lambda Example" }
        val x: () -> Unit = { println("sara") }

        // Higher-order functions
        println(calculate(10, 10, { x, y -> x + y }))
        println(calculate(10, 10, { x, y -> x - y }))
        println(calculate(10, 10, ::sum))


        // Extension function
        val list = mutableListOf(1, 2, 3)
        list.swap(0, 2)
        println(list)

        println("sarah".removeLastIndex())



        //anonymous  class
        val onClickListener = View.OnClickListener {
            object : View.OnClickListener {
                override fun onClick(v: View?) {

                }
            }
        }
        btnTest.setOnClickListener({ println("on click") })

        btnTest.viewTreeObserver.addOnDrawListener {
            anyMethod()
        }

        var lst: ArrayList<Int> = arrayListOf()
        for (number in 1..10) {
            lst.add(number)
        }

        val resultList = lst.filterOnCondition { isMultipleOf(it, 5) }
        println(resultList)

        var fruits = listOf<String>("Orange", "Banana", "Strawberry", "apple")
        fruits.filter { it.startsWith("a") }
            .map { it.uppercase() }
            .forEach { println(it) }

        // scope function

//        if (number != null) {
//            val number2 = number + 1
//        }
        val result = number?.let {
            val number2 = it + 1
            number2
        } ?: 3

   

        number.apply {
            this?.inc()
        }
        lifecycleScope.launch {
            helloWorld()
        }
        //  val viewModel: LoginViewModel by viewModels()

        val repo = UserRepository()
        val viewModel = MainViewModelFactory(repo).create(MainViewModel::class.java)

        viewModel.addUser("mohamed", 40)

        viewModel.successResult.observe(this, Observer {
            txtResult.text = "user added successfully"
        })

        viewModel.errorLiveData.observe(this, Observer {
            txtResult.text = "$it"
        })

    }

    fun anyMethod() {

    }

    suspend fun helloWorld() {
        coroutineScope {
            val job1 = launch {
                delay(2000L)
                println("World 2!")
            }

            val job2 = launch {
                delay(1000L)
                println("World 1!")
            }

            job2.cancel()
            print("Hello")
        }
    }


    fun ArrayList<Int>.filterOnCondition(condition: (Int) -> Boolean): ArrayList<Int> {
        val result: ArrayList<Int> = arrayListOf()
        for (item in this) {
            if (condition(item)) {
                result.add(item)
            }
        }
        return result
    }

    fun isMultipleOf(number: Int, multipleOf: Int): Boolean {
        return number % multipleOf == 0
    }

    //Extention function
    fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    //Extention function
    fun String.removeLastIndex(): String {
        return this.substring(0, this.length - 1)
    }

    companion object {
        private const val TAG = "MainActivity"
    }


    fun sum(x: Int, y: Int) = x + y

    //simple function
    fun generateNewString(count: Int): String =
        when (count) {
            10 -> "sara"
            20 -> "ahmed"
            else -> "any"
        }

    // Higher-order functions
    fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
        return operation(x, y)
    }


}