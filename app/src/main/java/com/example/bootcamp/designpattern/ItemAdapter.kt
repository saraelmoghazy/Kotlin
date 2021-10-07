package com.example.bootcamp.designpattern

class ItemAdapter(val databaseItem: DatabaseItem) {

    fun getItem(): UIItem {
        return UIItem(
            databaseItem.name,
            "${databaseItem.birthDay - databaseItem.birthMonth - databaseItem.birthYear}"
        )
    }
}