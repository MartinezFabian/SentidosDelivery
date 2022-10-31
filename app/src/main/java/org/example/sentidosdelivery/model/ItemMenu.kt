package org.example.sentidosdelivery.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemMenu(
    var categoria: String = "",
    var id: Int = 0,
    var ingredientes: String = "",
    var nombre: String = "",
    var precio: Int = 0,
    ) : Parcelable
