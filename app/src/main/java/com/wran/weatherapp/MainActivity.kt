package com.wran.weatherapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), MainScreenFragment.MainScreenFragmentListener, WeatherInfoFragment.WeatherInforListener {

    override fun confirmCity(city: String) {
        val key = "cfb6b8abd84948027d6ac97439d80ba7"
        val server = "https://api.openweathermap.org/data/2.5/forecast?"
        val url = server + "q=" + city + "&appid=" + key

        val apiCaller = ApiCaller(url)
        apiCaller.setUIActivity(this)
        apiCaller.call()

        val transaction = manager.beginTransaction()
        transaction.replace(R.id.main_frame, weatherInfoFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val manager :FragmentManager  = supportFragmentManager
    private val mainScreenFragment = MainScreenFragment()
    val weatherInfoFragment = WeatherInfoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = manager.beginTransaction()
        transaction.replace(R.id.main_frame, mainScreenFragment)
        transaction.commit()
    }

    fun updateWeatherInfo(info: WeatherInfoDto){
        weatherInfoFragment.updateView(info)
    }

}
