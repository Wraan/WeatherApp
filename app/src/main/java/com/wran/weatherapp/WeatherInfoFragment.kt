package com.wran.weatherapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wran.weatherapp.R.id.container
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
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    fun updateView(info: WeatherInfo){
        textLabel.text = info.string
    }

}
