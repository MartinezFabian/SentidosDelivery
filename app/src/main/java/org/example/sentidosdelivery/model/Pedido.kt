package org.example.sentidosdelivery.model

data class Pedido(
    val nombreCliente: String = "",
    var lista_productos: String,
    val precioTotal: Int = 0,
    val precioDelivery: Int = 0,
    val direccion: String = ""
)
