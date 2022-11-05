package org.example.sentidosdelivery.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.model.Usuario
import org.w3c.dom.Text


var usuario: Usuario? = null

class Perfil : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_perfil, container, false)


        val bundle = arguments

        if(bundle != null) {
            usuario = bundle.getParcelable<Usuario?>("usuario")

        }

        val tvNombreUsuario = rootView.findViewById<TextView>(R.id.tvNombreUsuario)
        tvNombreUsuario.setText(usuario?.nombre)

        val tvDNI = rootView.findViewById<TextView>(R.id.tvDni)
        tvDNI.setText(usuario?.dni)

        val tvApellido = rootView.findViewById<TextView>(R.id.tvApellido)
        tvApellido.setText(usuario?.apellido)

        val tvCelular = rootView.findViewById<TextView>(R.id.tvCelular)
        tvCelular.setText(usuario?.celular)

        val tvDomicilio = rootView.findViewById<TextView>(R.id.tvDomicilio)
        tvDomicilio.setText(usuario?.domicilio)

        val tvCorreoElectronico = rootView.findViewById<TextView>(R.id.tvCorreoElectronico)
        tvCorreoElectronico.setText(usuario?.email)

        return rootView
    }
}