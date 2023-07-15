package com.example.examenib.adapter

import android.content.Intent
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.TextureView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.ActualizarAlmacen
import com.example.examenib.ActualizarElectrodomestico
import com.example.examenib.ListaProductos
import com.example.examenib.R
import com.example.examenib.models.Almacen
import com.example.examenib.models.Electrodomestico
import com.example.examenib.providers.AlmacenProvider

class ElectrodomesticoViewHolder(view: View) : RecyclerView.ViewHolder(view),
    View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    val txtIdProducto = view.findViewById<TextView>(R.id.txt_electrodomestico_id)
    val txtNombreProducto = view.findViewById<TextView>(R.id.txt_nombre_producto)
    val txtCantidad = view.findViewById<TextView>(R.id.txt_cantidad)
    val txtprecio = view.findViewById<TextView>(R.id.txt_precio_producto)
    val txtCategoria = view.findViewById<TextView>(R.id.txt_categoria)
    val isAvailable = view.findViewById<TextView>(R.id.txt_Disponibilidad)

    init {
        view.setOnCreateContextMenuListener(this)
    }

    companion object {
        private lateinit var adapter: ElectrodomesticoAdapter

        fun setAdapter(electrodomesticoAdapter: ElectrodomesticoAdapter) {
            adapter = electrodomesticoAdapter
        }
    }

    fun render(electrodomestico: Electrodomestico) {
        txtIdProducto.text = electrodomestico.productID.toString()
        txtNombreProducto.text = electrodomestico.productName
        txtCantidad.text = "Cantidad: ${electrodomestico.cantidad.toString()}"
        txtprecio.text = electrodomestico.price.toString()
        txtCategoria.text = "Categoria ${electrodomestico.productType.toString()}"
        isAvailable.text =
            if (electrodomestico.isAvailable == true) "Disponible" else "No disponible"
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater = MenuInflater(v?.context)
        inflater.inflate(R.menu.menu_producto, menu)

        if (menu != null) {
            for (i in 0 until menu.size()) {
                menu.getItem(i).setOnMenuItemClickListener(this)
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editar_producto_tool -> {
                val idAlmacen = ListaProductos.idAlmacenAux.toString()
                val idElectro = txtIdProducto.text.toString()
                val intent = Intent(itemView.context, ActualizarElectrodomestico::class.java)
                intent.putExtra("idA", idAlmacen)
                intent.putExtra("idE", idElectro)
                itemView.context.startActivity(intent)
                true
            }

            R.id.eliminar_producto_tool -> {
                val idAlmacen = ListaProductos.idAlmacenAux

                val almacen =
                    AlmacenProvider.almacenesList.find { almacen: Almacen ->
                        almacen.storeID == idAlmacen
                    }
                val producto = almacen?.products?.find { producto ->
                    producto.productID == txtIdProducto.text.toString().toInt()
                }
                if (producto != null) {
                    almacen.products!!.remove(producto)
                }
                adapter.notifyDataSetChanged()
                true
            }

            else -> false
        }
    }


}