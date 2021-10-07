package com.example.bootcamp.designpattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bootcamp.R
import kotlinx.android.synthetic.main.activity_design_pattern.*

class DesignPatternActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_design_pattern)

        // builder
        Laptop.Builder("sara").setRam("100").create()



        btnSinglton.setOnClickListener {
            // singlton
            DataManager.getUserData()
        }

        //Factory
        val word = DocumentFactory.getDocument("word")
        word.showDocumentInfo()

        //Adapter
        // client is DatabaseItem , Adaptee UIItem , adapter is ItemAdapter
        val item = DatabaseItem("sara", 23, 11, 1987)
        val adapter = ItemAdapter(item)
        val uiItem = adapter.getItem()

    }
}