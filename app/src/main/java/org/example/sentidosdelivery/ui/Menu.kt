package org.example.sentidosdelivery.ui

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.adapter.MenuAdapter
import org.example.sentidosdelivery.adapter.MenuAdapterListener
import org.example.sentidosdelivery.model.ItemMenu

class Menu : Fragment(), MenuAdapterListener {

    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var lista_menu: ArrayList<ItemMenu>
    private val menuAdapter: MenuAdapter = MenuAdapter(this@Menu)

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
        menuRecyclerView.adapter = menuAdapter

        lista_menu = arrayListOf<ItemMenu>()

        getMenuData()

        //Buscar menu especifico en el listado de menus

        val etFiltro = rootView.findViewById<EditText>(R.id.etFiltro)

        etFiltro.addTextChangedListener { menuFiltro ->
            val menuFiltrado = lista_menu.filter {
                menu -> menu.nombre.lowercase().contains(menuFiltro.toString())
            }
            menuAdapter.updateMenus(menuFiltrado as ArrayList<ItemMenu>)
        }

        return rootView;
    }

    private fun getMenuData() {
        val databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://restaurantesentidos-190d3-default-rtdb.firebaseio.com/")

        databaseReference.child("menu").addListenerForSingleValueEvent(object : ValueEventListener{
            
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()) {
                    var bandera = false;

                    for (menuSnapshot in snapshot.children) {

                        if(bandera)
                        {
                            val menu = menuSnapshot.getValue(ItemMenu::class.java)

                            if(menu?.categoria == "comida")
                            {
                                lista_menu.add(menu!!)
                            }

                        }

                        bandera = true
                    }

                    menuAdapter.listaMenu = lista_menu
                    menuAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun onBuyMenuClicked(itemMenu: ItemMenu) {
        val intent = Intent(context, MenuItemDetailActivity::class.java)
        intent.putExtra("menu", itemMenu)
        startActivity(intent)
    }
}