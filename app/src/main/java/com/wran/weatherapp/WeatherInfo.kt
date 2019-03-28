package com.wran.weatherapp

import com.google.gson.Gson

class WeatherInfo{

    var string = ""

    fun parseJson(json: String): WeatherInfoDto {
        this.string = json

        var gson = Gson()
        return gson?.fromJson(json, WeatherInfoDto::class.java)
    }
}