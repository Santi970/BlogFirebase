package com.example.navigationcomponentsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController


class PrimerFragment : Fragment(R.layout.fragment_primer) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.btn_navegar)
        val resultado = view.findViewById<TextView>(R.id.txt_output)

        //Este escucha hasta que el dato viene de otro fragmento. Este se dispara cada vez que pongamos algo en el fragmentResult.
        setFragmentResultListener("requestKey"){ key, bundle  ->
            val result = bundle.getString("bundleKey")

            resultado.text = result


        }

        button.setOnClickListener{
          val action = PrimerFragmentDirections.actionPrimerFragmentToSegundoFragment("Santiago", 30)
            findNavController().navigate(action)
        }

    }
}

