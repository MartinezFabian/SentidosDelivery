package org.example.sentidosdelivery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.model.ItemMenu

class CarritoAdapter(private val itemCarritoAdapterListener:(Int) -> Unit) : RecyclerView.Adapter<CarritoAdapter.MyViewHolder>() {

    var listaCarrito: ArrayList<ItemMenu> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)

        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: CarritoAdapter.MyViewHolder, position: Int) {
        val itemActual = listaCarrito[position]

        holder.tvNombreMenu.text = itemActual.nombre
        holder.tvPrecioMenu.text =  "$" + itemActual.precio.toString()

        holder.bind(itemActual, itemCarritoAdapterListener)
    }

    override fun getItemCount(): Int {
        return listaCarrito.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNombreMenu: TextView = itemView.findViewById(R.id.tvNombreMenuCarrito)
        val tvPrecioMenu: TextView = itemView.findViewById(R.id.tvPrecioMenuCarrito)
        val btnEliminarDelCarrito: ImageView = itemView.findViewById(R.id.btnEliminarDelCarrito)

        fun bind(itemMenu: ItemMenu, itemCarritoAdapterListener: (Int) -> Unit) {
            btnEliminarDelCarrito.setOnClickListener{
                itemCarritoAdapterListener(adapterPosition)
            }
        }
    }
}