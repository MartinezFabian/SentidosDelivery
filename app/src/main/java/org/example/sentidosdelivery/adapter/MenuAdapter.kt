package org.example.sentidosdelivery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.model.ItemMenu

class MenuAdapter(private val listaMenu: ArrayList<ItemMenu> ) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemActual = listaMenu[position]

        holder.tvNombreMenu.text = itemActual.nombre
        holder.tvPrecioMenu.text = itemActual.precio.toString()
        holder.tvDescripcionMenu.text = itemActual.ingredientes

    }

    override fun getItemCount(): Int {
        return listaMenu.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNombreMenu: TextView = itemView.findViewById(R.id.tvNombreMenu)
        val tvPrecioMenu: TextView = itemView.findViewById(R.id.tvPrecioMenu)
        val tvDescripcionMenu: TextView = itemView.findViewById(R.id.tvDescripcionMenu)


    }
}