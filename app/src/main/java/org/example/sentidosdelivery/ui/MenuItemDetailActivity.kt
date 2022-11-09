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
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.text.SimpleDateFormat
import java.util.*

class MenuItemDetailActivity : AppCompatActivity() {

    private var menu: ItemMenu? = null
    private var cantidadPedido : Int = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_item_detail)

        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener{
            onBackPressed()
        }


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
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
        val simpleDateFormat = SimpleDateFormat("HH")
        val currentDateandTime: String = simpleDateFormat.format(Date())

        if(currentDateandTime.equals("23") ||
            currentDateandTime.equals("00") ||
            currentDateandTime.equals("01") ||
            currentDateandTime.equals("02") ||
            currentDateandTime.equals("03") ||
            currentDateandTime.equals("04") ||
            currentDateandTime.equals("05") ||
            currentDateandTime.equals("06") ||
            currentDateandTime.equals("07") ||
            currentDateandTime.equals("08") ){

            MotionToast.createColorToast(
                this,
                "Servicio no disponible",
                "No se pueden hacer pedidos despues de las 23 horas",
                MotionToastStyle.INFO,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                null
            )

        }else{
            if(menu != null){

                for(i in 1..cantidadPedido)
                {
                    lista_carrito.add(menu!!)
                }
                //Toast.makeText(this, "Agregado al carrito", Toast.LENGTH_SHORT).show()

                MotionToast.createColorToast(
                    this,
                    "Agregado al carrito",
                    "Se agrego al carrito con exito",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    null
                )
            }
        }

    }
}