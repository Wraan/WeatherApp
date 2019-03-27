package com.wran.weatherapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main_screen.view.*

class MainScreenFragment : Fragment() {
    private var listener: MainScreenFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_screen, container, false)
        view.cityConfirmBtn.setOnClickListener{
            listener?.confirmCity(view.cityTF.text.toString().trim())
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainScreenFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface MainScreenFragmentListener {
        fun confirmCity(city: String)
    }
}
