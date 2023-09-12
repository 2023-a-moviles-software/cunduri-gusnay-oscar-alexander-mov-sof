package com.example.examenib.adapter

import android.content.Intent
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.ActualizarElectrodomestico
import com.example.examenib.R
import com.example.examenib.SQLiteHelper
import com.example.examenib.models.Electrodomestico

class ElectrodomesticoViewHolder(view: View, dbHelper: SQLiteHelper) : RecyclerView.ViewHolder(view),
    View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    val txtIdProducto = view.findViewById<TextView>(R.id.txt_electrodomestico_id)
    val txtNombreProducto = view.findViewById<TextView>(R.id.txt_nombre_producto)
    val txtCantidad = view.findViewById<TextView>(R.id.txt_cantidad)
    val txtprecio = view.findViewById<TextView>(R.id.txt_precio_producto)
    val txtCategoria = view.findViewById<TextView>(R.id.txt_categoria)
    val isAvailable = view.findViewById<TextView>(R.id.txt_Disponibilidad)
    private val dbHelper: SQLiteHelper = dbHelper

    init {
        view.setOnCreateContextMenuListener(this)
    }

    companion object {
        private lateinit var adapter: ElectrodomesticoAdapter
        private var idAlmacen: Int = 0

        fun setAdapter(electrodomesticoAdapter: ElectrodomesticoAdapter) {
            adapter = electrodomesticoAdapter
        }

        fun setAlmacenID(id: Int){
            idAlmacen = id
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
                val idElectro = txtIdProducto.text.toString()
                val intent = Intent(itemView.context, ActualizarElectrodomestico::class.java)
                intent.putExtra("idE", idElectro)
                itemView.context.startActivity(intent)
                true
            }

            R.id.eliminar_producto_tool -> {
                val idAlmacen = idAlmacen
                val idProducto = txtIdProducto.text.toString().toInt()
                dbHelper.deleteElectrodomesticoInAlmacen(idAlmacen, idProducto)

                val newData = dbHelper.getElectrodomesticosByAlmacenId(idAlmacen)
                adapter.updateData(newData)
                true
            }

            else -> false
        }
    }


}
