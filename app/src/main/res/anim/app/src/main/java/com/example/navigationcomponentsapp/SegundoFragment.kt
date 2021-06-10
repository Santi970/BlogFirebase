package com.example.navigationcomponentsapp

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class SegundoFragment : Fragment(R.layout.fragment_segundo) {


    private var nombre: String? = ""   //si es nulo entrega vacio
    private var edad: Int? = 0       //si es nula le asignamos valor 0
    private val args: SegundoFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            nombre = args.nombre
            edad = args.edad


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val texto  = view.findViewById<TextView>(R.id.txt_output)

        val button = view.findViewById<Button>(R.id.btn_enviarDatos)
        button.setOnClickListener{
            val result = "Esto esta en la variable result del bundle"
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            findNavController().navigate(Uri.parse("cursoandroid://card"))
        }

        texto.text = "$nombre $edad"



    }

    //claves para luego utilizar y acceder a los datos que van a estar almacenados en los argumentos
    companion object {
        private const val MI_NOMBRE = "nombre"
        private const val MI_EDAD = "edad"

        //metodo que hace referencia a la nueva instancia del segundo fragment.
        fun newInstance(nombre: String, edad: Int) = SegundoFragment().apply {  //el return puede ser con el = o la palabra "return"
            arguments = bundleOf( MI_NOMBRE to nombre, MI_EDAD to edad)   //bundleOf crea un set de clave valor
        }
    }
}
