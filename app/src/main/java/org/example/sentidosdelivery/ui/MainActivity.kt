package org.example.sentidosdelivery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.model.Usuario

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottonNavigationView)
        val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.menu, R.id.carrito, R.id.perfil))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)


        val usuario = intent.getParcelableExtra<Usuario>("usuario")

        Toast.makeText(this, usuario!!.nombre, Toast.LENGTH_SHORT).show()

        val fmanager = supportFragmentManager
        val fmanagertrans = fmanager.beginTransaction()
        val fragment = Perfil()

        val databundle = Bundle()

        databundle.putParcelable("usuario", usuario)
        fragment.arguments = databundle
        fmanagertrans.add(R.id.fragment, fragment).commit()

    }
}

