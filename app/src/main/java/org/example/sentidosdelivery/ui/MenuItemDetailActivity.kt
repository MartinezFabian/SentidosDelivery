package org.example.sentidosdelivery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.model.ItemMenu
import org.example.sentidosdelivery.model.Usuario

class MenuItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_item_detail)

        val menu = intent.getParcelableExtra<ItemMenu>("menu")

        val tvNombreMenu = findViewById<TextView>(R.id.tvNombreMenuDetailActivity)
        val tvPrecioMenu = findViewById<TextView>(R.id.tvPrecioMenuDetailActivity)
        val tvDescripcionMenu = findViewById<TextView>(R.id.tvDescripcionActivityDetail)

        tvNombreMenu.setText(menu!!.nombre)
        tvPrecioMenu.setText("$" + menu!!.precio.toString())
        tvDescripcionMenu.setText(menu!!.ingredientes)

    }
}