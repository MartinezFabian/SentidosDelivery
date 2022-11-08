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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.example.sentidosdelivery.R
import org.example.sentidosdelivery.adapter.CarritoAdapter
import org.example.sentidosdelivery.model.ItemMenu
import org.example.sentidosdelivery.model.Pedido
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

 var lista_carrito: ArrayList<ItemMenu> = arrayListOf<ItemMenu>()
 var carritoAdapter: CarritoAdapter? = null

class Carrito : Fragment(), RadioGroup.OnCheckedChangeListener {

    private lateinit var carritoRecyclerView: RecyclerView

    private val menuItemDetailActivity:MenuItemDetailActivity = MenuItemDetailActivity()

    var tvPrecioTotal: TextView? = null
    var precioTotal: Int = 0

    var radioGroup: RadioGroup? = null
    var rbEnvioAdomicilio: RadioButton? = null
    var rbRetirarEnRestaurante: RadioButton? = null
    var tvPrecioDelivery: TextView? = null
    var precioDelivery = 0.0

    var envioAdomicilio = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View = inflater.inflate(R.layout.fragment_carrito, container, false)

        //RecyclerView Listado Carrito
        carritoAdapter = CarritoAdapter(
            itemCarritoAdapterListener = {position -> onDeleteitemCarrito(position)}
        )

        carritoRecyclerView = rootView.findViewById(R.id.recyclerCarrito)
        carritoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        carritoRecyclerView.setHasFixedSize(true)
        carritoRecyclerView.adapter = carritoAdapter

        menuItemDetailActivity.cargarACarrito()

        carritoAdapter!!.listaCarrito = lista_carrito
        carritoAdapter!!.notifyDataSetChanged()


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
                //Toast.makeText(requireContext(), "¡Su carrito está vacío!", Toast.LENGTH_SHORT).show()

                MotionToast.createColorToast(
                    requireActivity(),
                    "¡Su carrito está vacío!",
                    "Agregue productos al carrito para realizar un pedido",
                    MotionToastStyle.INFO,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    null
                )

            }else if(rbEnvioAdomicilio!!.isChecked || rbRetirarEnRestaurante!!.isChecked) {

                var productos: String = ""
                val pedido: Pedido;

                //Se pasa a string el pedido
                for (item in lista_carrito){
                    productos = productos + item.nombre + ", "
                }

                if(rbEnvioAdomicilio!!.isChecked){
                    pedido = Pedido(usuario!!.nombre, productos, precioTotal, precioDelivery, "")
                }else{
                    pedido = Pedido(usuario!!.nombre, productos, precioTotal, 0.0, "")
                }

                val intent = Intent(context, PagoActivity::class.java)
                intent.putExtra("ENVIO_DOMICILIO", envioAdomicilio)
                intent.putExtra("PEDIDO", pedido)
                startActivity(intent)

            } else{
                //Toast.makeText(requireContext(), "Seleccione si quiere retirar en el restaurante o envío a domicilio", Toast.LENGTH_SHORT).show()

                MotionToast.createColorToast(
                    requireActivity(),
                    "Elija una de las opciones",
                    "Seleccione envío a domicilio o retirar en restaurante",
                    MotionToastStyle.INFO,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    null
                )

            }
        }


        return rootView
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when(checkedId){
            rbRetirarEnRestaurante?.id -> {
                tvPrecioDelivery!!.setText("$0.0")
                envioAdomicilio = false
            }

            rbEnvioAdomicilio?.id -> {
                precioDelivery = precioTotal * 0.01

                tvPrecioDelivery!!.setText("$ %.2f".format(precioDelivery))

                envioAdomicilio = true
            }
        }
    }

    private fun onDeleteitemCarrito(position: Int){
        lista_carrito.removeAt(position)
        carritoAdapter!!.notifyItemRemoved(position)

        precioTotal = 0

        for (item in lista_carrito)
        {
            precioTotal += item.precio
        }

        if(lista_carrito.isNotEmpty())
        {
            tvPrecioTotal!!.setText("$"+precioTotal.toString())

        }else{
            tvPrecioTotal!!.setText("$0")
        }

        if(rbEnvioAdomicilio!!.isChecked){
            precioDelivery = precioTotal * 0.01

            tvPrecioDelivery!!.setText("$ %.2f".format(precioDelivery))
        }
    }

}