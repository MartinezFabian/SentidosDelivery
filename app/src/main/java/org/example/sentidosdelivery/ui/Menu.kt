package org.example.sentidosdelivery.ui

import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
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

        getMenuData()

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

                    menuRecyclerView.adapter = MenuAdapter(lista_menu)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}