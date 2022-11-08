package org.example.sentidosdelivery.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.model.Pedido
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.text.SimpleDateFormat
import java.util.*


class PagoActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)

        val etNumTarjeta = findViewById<EditText>(R.id.etNumTarjeta)
        val etTitularTarjeta = findViewById<EditText>(R.id.etTitularTarjeta)
        val etFechaVencimiento = findViewById<EditText>(R.id.etFechaVencimiento)
        val etCodSeguridad = findViewById<EditText>(R.id.etCodSeguridad)
        val etDNI = findViewById<EditText>(R.id.etDNI)
        val etDireccionEnvio = findViewById<EditText>(R.id.etDireccionEnvio)

        val envioAdomicilio = intent.getBooleanExtra("ENVIO_DOMICILIO", false)
        val pedido = intent.getParcelableExtra<Pedido>("PEDIDO")

        if(envioAdomicilio == true){
            etDireccionEnvio.visibility = View.VISIBLE

        }else{
            etDireccionEnvio.visibility = View.INVISIBLE
        }

        val btnPagar = findViewById<Button>(R.id.btnPagar)

        btnPagar.setOnClickListener {

            if (etNumTarjeta.text.isNotEmpty() &&
                    etTitularTarjeta.text.isNotEmpty() &&
                    etFechaVencimiento.text.isNotEmpty() &&
                    etCodSeguridad.text.isNotEmpty() &&
                    etDNI.text.isNotEmpty()){

                TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
                val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                val currentDateandTime: String = simpleDateFormat.format(Date())

                if(envioAdomicilio == true) {
                    if(etDireccionEnvio.text.isNotEmpty()){

                        pedido!!.direccion = etDireccionEnvio.text.toString()

                        database = FirebaseDatabase.getInstance().getReference("PedidosApp")
                        database.child(currentDateandTime).setValue(pedido).addOnSuccessListener {
                            MotionToast.createColorToast(
                                this,
                                "Pago Realizado con Éxito",
                                "Factura enviada a " + usuario!!.email,
                                MotionToastStyle.SUCCESS,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                null
                            )

                            lista_carrito.clear()
                            carritoAdapter!!.notifyDataSetChanged()
                        }

                        super.finish()

                    }

                }else{

                    database = FirebaseDatabase.getInstance().getReference("PedidosApp")
                    database.child(currentDateandTime).setValue(pedido).addOnSuccessListener {
                        MotionToast.createColorToast(
                            this,
                            "Pago Realizado con Éxito",
                            "Factura enviada a " + usuario!!.email,
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            null
                        )

                        lista_carrito.clear()
                        carritoAdapter!!.notifyDataSetChanged()
                    }

                    super.finish()

                }

            }else{
                /*Toast.makeText(this,
                    "Complete todos los datos de su tarjeta para poder completar el pago",
                    Toast.LENGTH_LONG).show() */

                MotionToast.createColorToast(
                    this,
                    "Complete todos los campos",
                    "Complete todos los datos de su tarjeta",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    null
                )
            }

        }

    }
}