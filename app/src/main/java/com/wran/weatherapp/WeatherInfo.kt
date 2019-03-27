package com.wran.weatherapp

class WeatherInfo{
    var string = ""
    fun parseJson(json: String) {
        this.string = json
    }
}