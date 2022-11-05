package org.example.sentidosdelivery.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.model.Usuario
import org.jetbrains.annotations.NotNull
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class Login : AppCompatActivity() {

    private val databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://restaurantesentidos-190d3-default-rtdb.firebaseio.com/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etNombreUsuario = findViewById<EditText>(R.id.etNombreUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        val listaUsuarios = arrayListOf<Usuario>()

        btnIngresar.setOnClickListener{
            val nombreUsuario = etNombreUsuario.text.toString()
            val password = etPassword.text.toString()
            var usuarioEncontrado = false
            var usuario: Usuario? = null

            if(nombreUsuario.isEmpty() || password.isEmpty()){

                //Toast.makeText(this, "Por favor ingrese su usuario y contraseña", Toast.LENGTH_SHORT).show()

                MotionToast.createColorToast(
                    this@Login,
                    "Error en el inicio de sesión",
                    "Ingrese su usuario y contraseña",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    null
                )

            }else{
                databaseReference.child("usuarios").addListenerForSingleValueEvent(object: ValueEventListener{

                    override fun onDataChange(snapshot: DataSnapshot) {
                            //Traer usuarios de firebase y guardarlos en listaUsuarios

                            for (usuarioSnapshot in snapshot.children)
                            {
                                val usuario = usuarioSnapshot.getValue(Usuario::class.java)

                                listaUsuarios.add(usuario!!)

                            }

                            for (item in listaUsuarios)
                            {

                                    if(item.nombre == nombreUsuario && item.password == password && item.rol == "cliente")
                                    {
                                        usuarioEncontrado = true

                                        usuario = item

                                        break
                                    }

                            }

                            if(usuarioEncontrado)
                            {
                                val intent = Intent(this@Login, MainActivity::class.java)
                                intent.putExtra("usuario", usuario)
                                startActivity(intent)
                                finish()
                            }
                            else
                            {
                                //Toast.makeText(this@Login, "Usuario y/o Contraseña incorrecta", Toast.LENGTH_SHORT).show()

                                MotionToast.createColorToast(
                                    this@Login,
                                    "Error en el inicio de sesión",
                                    "Usuario y/o Contraseña incorrecta",
                                    MotionToastStyle.ERROR,
                                    MotionToast.GRAVITY_BOTTOM,
                                    MotionToast.SHORT_DURATION,
                                    null
                                )
                            }
                    }


                    override fun onCancelled(error: DatabaseError) {

                    }


                })

            }


        }

    }
}