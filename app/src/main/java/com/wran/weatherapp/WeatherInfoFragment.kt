package com.wran.weatherapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_weather_info.*


class WeatherInfoFragment : Fragment() {
    private var listener: WeatherInforListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather_info, container, false)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is WeatherInforListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface WeatherInforListener {
        fun onFragmentInteraction(uri: Uri)
    }

    fun updateView(info: WeatherInfoDto) {
        updateIcons(info)
        updateHours(info)
        updateTemperatures(info)
        updateCurrentInfo(info)
    }

    private fun updateIcons(info: WeatherInfoDto) {
        var icons = arrayOf(forecast0Icon, forecast1Icon, forecast2Icon, forecast3Icon, forecast4Icon )
        for (i in 0 until icons.size) {
            icons[i].setImageResource(R.drawable.few_clouds)
            Log.d("info", i.toString() + ": " + info.list[i].weather[0].id.toString())

            if (info.list[i].weather[0].id / 100 == 8) {
                icons[i].setImageResource(
                    when (info.list[i].weather[0].id % 800) {
                        0 -> R.drawable.clear_sky
                        1 -> R.drawable.few_clouds
                        2 -> R.drawable.scattered_clouds
                        3 -> R.drawable.broken_clouds
                        4 -> R.drawable.broken_clouds
                        else -> R.drawable.clear_sky
                    }
                )
            } else {
                icons[i].setImageResource(
                    when (info.list[i].weather[0].id / 100) {
                        2 -> R.drawable.thunderstorm
                        3 -> R.drawable.shower_rain
                        5 -> R.drawable.rain
                        6 -> R.drawable.snow
                        7 -> R.drawable.mist
                        else -> R.drawable.clear_sky
                    }
                )
            }
        }
    }

    private fun updateHours(info: WeatherInfoDto) {
        forecast1Hour.text = info.list[1].dtTxt.split(" ")[1].substring(0,5)
        forecast2Hour.text = info.list[2].dtTxt.split(" ")[1].substring(0,5)
        forecast3Hour.text = info.list[3].dtTxt.split(" ")[1].substring(0,5)
        forecast4Hour.text = info.list[4].dtTxt.split(" ")[1].substring(0,5)
    }

    private fun updateTemperatures(info: WeatherInfoDto) {
        forecast1Temp.text = info.list[1].main.temp.toString() + "\u00B0C"
        forecast2Temp.text = info.list[2].main.temp.toString() + "\u00B0C"
        forecast3Temp.text = info.list[3].main.temp.toString() + "\u00B0C"
        forecast4Temp.text = info.list[4].main.temp.toString() + "\u00B0C"
    }

    private fun updateCurrentInfo(info: WeatherInfoDto) {
        cityName.text = info.city.name
        infoDate.text = info.list[0].dtTxt
        infoDesc.text = info.list[0].weather[0].description
        infoTemp.text = info.list[0].main.temp.toString() + "\u00B0C"
        infoWind.text = info.list[0].wind.speed.toString() + " m/s"
        infoPressure.text = info.list[0].main.pressure.toString() + " hpa"
        infoHumidity.text = info.list[0].main.humidity.toString() + " %"
    }

}
