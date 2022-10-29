package org.example.sentidosdelivery.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario (
    var apellido: String = "",
    var celular: String = "",
    var comentarios: String = "",
    var dni: String = "",
    var domicilio: String = "",
    var email: String = "",
    var id: Int = 0,
    var nombre: String = "",
    var password: String = "",
    var reservasPagadas: List<String>? = null,
    var reservasSinPagar: List<String>? = null,
    var rol: String = ""

        ) : Parcelable