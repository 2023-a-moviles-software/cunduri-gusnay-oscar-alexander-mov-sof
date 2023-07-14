package com.example.examenib.adapter


import android.content.Intent
import android.view.ContextMenu

import android.view.MenuInflater
import android.view.MenuItem

import android.view.View

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.example.examenib.ListaProductos

import com.example.examenib.R
import com.example.examenib.models.Almacen

class AlmacenViewHolder(view: View) : RecyclerView.ViewHolder(view),
    View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    private val idAlmacen = view.findViewById<TextView>(R.id.txt_id)
    private val nombreAlmacen = view.findViewById<TextView>(R.id.txt_name)
    private val ubicacionAlmacen = view.findViewById<TextView>(R.id.txt_Direccion)
    private val isOpenAlmacen = view.findViewById<TextView>(R.id.txt_isOpen)

    init {
        view.setOnCreateContextMenuListener(this)
    }

    fun render(almacen: Almacen) {
        idAlmacen.text = "Almacen ${almacen.storeID.toString()}"
        nombreAlmacen.text = almacen.storeName
        ubicacionAlmacen.text = almacen.storeLocation
        isOpenAlmacen.text = if (almacen.isOpen) "Abierto" else "Cerrado"
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater = MenuInflater(v?.context)
        inflater.inflate(R.menu.menu_tools, menu)

        if (menu != null) {
            for (i in 0 until menu.size()) {
                menu.getItem(i).setOnMenuItemClickListener(this)
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ver_productos -> {
                val idAlmacen = idAlmacen // Obtén el idAlmacen del objeto Almacen actual
                val intent = Intent(itemView.context, ListaProductos::class.java)
                intent.putExtra("id", idAlmacen.toString())
                itemView.context.startActivity(intent)
                true
            }

            R.id.editar_almacen -> {
                // Acción para editar almacén
                true
            }

            R.id.eliminar_almacen -> {
                // Acción para eliminar almacén
                true
            }

            else -> false
        }
    }
}

