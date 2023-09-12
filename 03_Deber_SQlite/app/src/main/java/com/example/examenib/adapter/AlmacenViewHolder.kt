package com.example.examenib.adapter


import android.annotation.SuppressLint
import android.content.Intent
import android.view.ContextMenu

import android.view.MenuInflater
import android.view.MenuItem

import android.view.View

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.ActualizarAlmacen

import com.example.examenib.ListaProductos

import com.example.examenib.R
import com.example.examenib.SQLiteHelper
import com.example.examenib.models.Almacen
import com.example.examenib.providers.AlmacenProvider

class AlmacenViewHolder(view: View, dbHelper: SQLiteHelper) : RecyclerView.ViewHolder(view),
    View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    private val idAlmacen = view.findViewById<TextView>(R.id.txt_id)
    private val nombreAlmacen = view.findViewById<TextView>(R.id.txt_name)
    private val ubicacionAlmacen = view.findViewById<TextView>(R.id.txt_Direccion)
    private val isOpenAlmacen = view.findViewById<TextView>(R.id.txt_isOpen)
    private val dbHelper: SQLiteHelper = dbHelper


    init {
        view.setOnCreateContextMenuListener(this)
    }

    companion object {
        private lateinit var adapter: AlmacenAdapter

        fun setAdapter(almacenAdapter: AlmacenAdapter) {
            adapter = almacenAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    fun render(almacen: Almacen) {
        idAlmacen.text = "Almacen ${almacen.id.toString()}"
        nombreAlmacen.text = almacen.storeName
        ubicacionAlmacen.text = almacen.storeLocation
        isOpenAlmacen.text = if (almacen.isOpen) "Abierto" else "Cerrado"

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
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
               val idAux = idAlmacen.text
                val nombreAlmacen = nombreAlmacen.text
                val intent = Intent(itemView.context, ListaProductos::class.java)
                intent.putExtra("idAProducto", idAux)
                intent.putExtra("nombre", nombreAlmacen)
                itemView.context.startActivity(intent)
                true
            }

            R.id.editar_almacen -> {
                val idAux = idAlmacen.text.split(" ")[1]
                val intent = Intent(itemView.context, ActualizarAlmacen::class.java)
                intent.putExtra("idAProducto", idAux)
                itemView.context.startActivity(intent)
                true
            }

            R.id.eliminar_almacen -> {
                val idAux = idAlmacen.text.split(" ").get(1).toInt()
                dbHelper.deleteAlmacen(idAux)
                val listAlmacenes = dbHelper.getAllAlmacenes()
                adapter.updateData(listAlmacenes)
                true
            }

            else -> false
        }
    }
}

