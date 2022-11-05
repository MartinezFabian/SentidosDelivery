package org.example.sentidosdelivery.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.example.sentidosdelivery.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class PagoActivity : AppCompatActivity() {
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

                if(envioAdomicilio == true) {
                    if(etDireccionEnvio.text.isNotEmpty()){
                        /*
                        Toast.makeText(this,
                            "¡Pago realizado con éxito! Se envió la factura a "+ usuario!!.email,
                            Toast.LENGTH_LONG).show()*/

                        MotionToast.createToast(
                            this,
                            "Pago Realizado con Éxito",
                            "Factura enviada a " + usuario!!.email,
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_CENTER,
                            MotionToast.LONG_DURATION,
                            null
                        )

                        lista_carrito.clear()

                        super.finish()

                    }

                }else{
                    /*
                    Toast.makeText(this,
                        "¡Pago realizado con éxito! Se envió la factura a "+ usuario!!.email,
                        Toast.LENGTH_LONG).show() */

                    MotionToast.createToast(
                        this,
                        "Pago Realizado con Éxito",
                        "Factura enviada a " + usuario!!.email,
                        MotionToastStyle.SUCCESS,
                        MotionToast.GRAVITY_CENTER,
                        MotionToast.LONG_DURATION,
                        null
                    )

                    lista_carrito.clear()

                    super.finish()

                }

            }else{
                /*Toast.makeText(this,
                    "Complete todos los datos de su tarjeta para poder completar el pago",
                    Toast.LENGTH_LONG).show() */

                MotionToast.createToast(
                    this,
                    "Complete todos los campos",
                    "Complete todos los datos de su tarjeta",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_CENTER,
                    MotionToast.SHORT_DURATION,
                    null
                )
            }

        }

    }
}