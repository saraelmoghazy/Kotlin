package com.example.bootcamp.designpattern

class Laptop(builder: Builder) {

    private val processor: String = builder.processor
    private val ram: String = builder.ram
    private val battery: String = builder.battery
    private val screenSize: String = builder.screenSize

    class Builder(processor: String) {
        var processor: String = processor // important param

        // optional param
        var ram: String = "2GB"
        var battery: String = "5000MAH"
        var screenSize: String = "15inch"

        // only setters, return builder
        fun setRam(ram: String): Builder {
            this.ram = ram
            return this
        }

        fun setBattery(battery: String): Builder {
            this.battery = battery
            return this
        }

        fun setScreenSize(screenSize: String): Builder {
            this.screenSize = screenSize
            return this
        }

        fun create(): Laptop {
            // create object of builder here
            return Laptop(this)
        }
    }
}