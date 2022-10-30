package org.example.sentidosdelivery.ui

import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.adapter.MenuAdapter
import org.example.sentidosdelivery.model.ItemMenu

class Menu : Fragment() {

    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var lista_menu: ArrayList<ItemMenu>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_menu, container, false)

        menuRecyclerView = rootView.findViewById(R.id.recyclerMenu)
        menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        menuRecyclerView.setHasFixedSize(true)

        lista_menu = arrayListOf<ItemMenu>()

        val hamburguesa: ItemMenu = ItemMenu("comida", 1, "Pan, Carne, Queso", "Hamburguesa", 999.9)

        lista_menu.add(hamburguesa)

        val adapter = MenuAdapter(lista_menu)
        menuRecyclerView.adapter = adapter

        return rootView;
    }
}