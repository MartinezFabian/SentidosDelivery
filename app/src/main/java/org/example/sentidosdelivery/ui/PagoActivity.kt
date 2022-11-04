package org.example.sentidosdelivery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.example.sentidosdelivery.R

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
                        Toast.makeText(this,
                            "¡Pago realizado con éxito! Se envió la factura a su correo electrónico",
                            Toast.LENGTH_LONG).show()
                    }

                }else{
                    Toast.makeText(this,
                        "¡Pago realizado con éxito! Se envió la factura a su correo electrónico",
                        Toast.LENGTH_LONG).show()
                }

            }else{
                Toast.makeText(this,
                    "Complete todos los datos de su tarjeta para poder completar el pago",
                    Toast.LENGTH_LONG).show()
            }

        }

    }
}