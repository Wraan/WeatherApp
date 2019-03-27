package com.wran.weatherapp

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class ApiCaller(private var url: String){
    private lateinit var view: MainActivity
    private val client = OkHttpClient()

    fun call(){
        val info = WeatherInfo()

        thread(start=true, isDaemon = true){
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()

            info.parseJson(response.body()!!.string())
            view.runOnUiThread {
                view.updateWeatherInfo(info)
            }

        }
    }

    fun setUIActivity(view: MainActivity){
        this.view = view
    }

}