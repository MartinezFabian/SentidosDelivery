package org.example.sentidosdelivery.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pedido(
    val nombreCliente: String = "",
    var pedido: String,
    val precioTotal: Int = 0,
    val precioDelivery: Double = 0.0,
    var direccion: String = ""
) : Parcelable
