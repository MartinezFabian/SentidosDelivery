package org.example.sentidosdelivery.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.model.ItemMenu
import org.example.sentidosdelivery.model.Usuario

class MenuItemDetailActivity : AppCompatActivity() {

    private var menu: ItemMenu? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_item_detail)

        menu = intent.getParcelableExtra<ItemMenu>("menu")!!

        val tvNombreMenu = findViewById<TextView>(R.id.tvNombreMenuDetailActivity)
        val tvPrecioMenu = findViewById<TextView>(R.id.tvPrecioMenuDetailActivity)
        val tvDescripcionMenu = findViewById<TextView>(R.id.tvDescripcionActivityDetail)

        tvNombreMenu.setText(menu!!.nombre)
        tvPrecioMenu.setText("$" + menu!!.precio.toString())
        tvDescripcionMenu.setText(menu!!.ingredientes)

        val btnMas = findViewById<ImageView>(R.id.btnMas)
        val btnMenos = findViewById<ImageView>(R.id.btnMenos)
        val tvCantidad = findViewById<TextView>(R.id.tvCantidadDetailActivity)

        var cantidadPedido = 1

        tvCantidad.text = cantidadPedido.toString()

        btnMas.setOnClickListener(View.OnClickListener{

            if(cantidadPedido < 10)
            {
                cantidadPedido++
                tvCantidad.text = cantidadPedido.toString()
            }
        })

        btnMenos.setOnClickListener(View.OnClickListener {

            if(cantidadPedido > 1)
            {
                cantidadPedido--
                tvCantidad.text = cantidadPedido.toString()
            }
        })

        val btnAgregarAlCarrito = findViewById<Button>(R.id.btnAgregarAlCarrito)

        btnAgregarAlCarrito.setOnClickListener{
            cargarACarrito()
            onBackPressed()
        }
    }

    fun cargarACarrito()
    {
        if(menu != null){
            lista_carrito.add(menu!!)
            Toast.makeText(this, "Agregado al carrito", Toast.LENGTH_SHORT).show()
        }

    }
}