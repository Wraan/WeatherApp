package com.wran.weatherapp

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class ApiCaller(private var url: String){
    private lateinit var view: MainActivity
    private val client = OkHttpClient()

    fun call(){
        thread(start=true, isDaemon = true){
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()

            val gson = Gson()
            val info = gson?.fromJson(response.body()!!.string(), WeatherInfoDto::class.java)

            view.runOnUiThread {
                view.updateWeatherInfo(info)
            }

        }
    }

    fun setUIActivity(view: MainActivity){
        this.view = view
    }

}