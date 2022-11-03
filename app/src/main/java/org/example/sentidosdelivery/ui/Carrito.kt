package org.example.sentidosdelivery.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.adapter.CarritoAdapter
import org.example.sentidosdelivery.model.ItemMenu

public var lista_carrito: ArrayList<ItemMenu> = arrayListOf<ItemMenu>()

class Carrito : Fragment(), RadioGroup.OnCheckedChangeListener {

    private lateinit var carritoRecyclerView: RecyclerView
    private val carritoAdapter: CarritoAdapter = CarritoAdapter()
    private val menuItemDetailActivity:MenuItemDetailActivity = MenuItemDetailActivity()

    var tvPrecioTotal: TextView? = null
    var precioTotal: Int = 0

    var radioGroup: RadioGroup? = null
    var rbEnvioAdomicilio: RadioButton? = null
    var rbRetirarEnRestaurante: RadioButton? = null
    var tvPrecioDelivery: TextView? = null
    var precioDelivery = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View = inflater.inflate(R.layout.fragment_carrito, container, false)

        //RecyclerView Listado Carrito
        carritoRecyclerView = rootView.findViewById(R.id.recyclerCarrito)
        carritoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        carritoRecyclerView.setHasFixedSize(true)
        carritoRecyclerView.adapter = carritoAdapter

        menuItemDetailActivity.cargarACarrito()

        carritoAdapter.listaCarrito = lista_carrito
        carritoAdapter.notifyDataSetChanged()

        //Calcular Precio Total
        tvPrecioTotal = rootView.findViewById(R.id.tvPrecioTotal)
        precioTotal = 0

        for (item in lista_carrito)
        {
            precioTotal += item.precio
        }

        if(lista_carrito.isNotEmpty())
        {
            tvPrecioTotal!!.setText("$"+precioTotal.toString())
        }

        //Calcular Precio delivery
        tvPrecioDelivery = rootView.findViewById(R.id.tvPrecioDelivery)
        rbRetirarEnRestaurante= rootView.findViewById(R.id.rbRetirarEnRestaurante)
        rbEnvioAdomicilio = rootView.findViewById(R.id.rbEnvioAdomicilio)
        radioGroup = rootView.findViewById(R.id.radio)

        radioGroup?.setOnCheckedChangeListener(this)

        //Boton Confirmar Pedido

        val btnConfirmarPedido = rootView.findViewById<Button>(R.id.btnConfirmarPedido)

        btnConfirmarPedido.setOnClickListener{

            if(lista_carrito.isEmpty())
            {
                Toast.makeText(requireContext(), "¡Su carrito está vacío!", Toast.LENGTH_SHORT).show()

            }else if(rbEnvioAdomicilio!!.isChecked || rbRetirarEnRestaurante!!.isChecked) {

                val intent = Intent(context, PagoActivity::class.java)
                //intent.putExtra()
                startActivity(intent)

            } else{
                Toast.makeText(requireContext(), "Seleccione si quiere retirar en el restaurante o envío a domicilio", Toast.LENGTH_SHORT).show()
            }
        }


        return rootView
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when(checkedId){
            rbRetirarEnRestaurante?.id -> {
                tvPrecioDelivery!!.setText("$0.0")
            }

            rbEnvioAdomicilio?.id -> {
                precioDelivery = precioTotal * 0.01

                tvPrecioDelivery!!.setText("$ %.2f".format(precioDelivery))
            }
        }
    }

}