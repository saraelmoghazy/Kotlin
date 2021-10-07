package com.example.bootcamp.designpattern

object DocumentFactory {

    fun getDocument(documentType: String): Document {
        when (documentType) {
            "word" -> return WordDocument()
            "draw" -> return DrawingDocument()
            else -> return WordDocument()
        }
    }
}