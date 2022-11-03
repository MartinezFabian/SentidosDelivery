package org.example.sentidosdelivery.ui

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.adapter.CarritoAdapter
import org.example.sentidosdelivery.model.ItemMenu

public var lista_carrito: ArrayList<ItemMenu> = arrayListOf<ItemMenu>()

class Carrito : Fragment() {

    private lateinit var carritoRecyclerView: RecyclerView
    private val carritoAdapter: CarritoAdapter = CarritoAdapter()
    private val menuItemDetailActivity:MenuItemDetailActivity = MenuItemDetailActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View = inflater.inflate(R.layout.fragment_carrito, container, false)

        carritoRecyclerView = rootView.findViewById(R.id.recyclerCarrito)
        carritoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        carritoRecyclerView.setHasFixedSize(true)
        carritoRecyclerView.adapter = carritoAdapter

        //lista_carrito = arrayListOf<ItemMenu>()

        menuItemDetailActivity.cargarACarrito()

        carritoAdapter.listaCarrito = lista_carrito
        carritoAdapter.notifyDataSetChanged()
        
        return rootView
    }

}