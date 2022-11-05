package org.example.sentidosdelivery.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.model.Usuario


class MainActivity : AppCompatActivity() {

    lateinit var transaction: FragmentTransaction;
    lateinit var fragmentMenu: Fragment;
    lateinit var fragmentCarrito: Fragment;
    lateinit var fragmentPerfil: Fragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Traer objeto usuario desde login y pasarlo a fragment Perfil
        val usuario = intent.getParcelableExtra<Usuario>("usuario")

        val fmanager = supportFragmentManager
        val fmanagertrans = fmanager.beginTransaction()
        val fragment = Perfil()

        val databundle = Bundle()

        databundle.putParcelable("usuario", usuario)
        fragment.arguments = databundle
        fmanagertrans.add(R.id.fragment, fragment).commit()

        //Navegacion
        fragmentMenu = Menu();
        fragmentCarrito = Carrito();
        fragmentPerfil = Perfil();

        fmanager.beginTransaction().add(R.id.fragment, fragmentPerfil).commit()
    }

    fun onClick(item: MenuItem) {
        transaction = supportFragmentManager.beginTransaction()

        when(item.itemId)
        {
            R.id.menu -> { transaction.replace(R.id.fragment, fragmentMenu)
            transaction.addToBackStack(null)}

            R.id.carrito -> {transaction.replace(R.id.fragment, fragmentCarrito)
                transaction.addToBackStack(null)}
            R.id.perfil -> {transaction.replace(R.id.fragment, fragmentPerfil)
                transaction.addToBackStack(null)}
        }
        transaction.commit()
    }
}

