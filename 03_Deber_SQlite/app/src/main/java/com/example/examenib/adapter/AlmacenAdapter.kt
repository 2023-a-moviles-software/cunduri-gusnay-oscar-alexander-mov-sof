package com.example.examenib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.R
import com.example.examenib.SQLiteHelper
import com.example.examenib.models.Almacen

class AlmacenAdapter(
    private var almacenList: List<Almacen>,
    private val dbHelper: SQLiteHelper // Agregamos el dbHelper como parámetro
) : RecyclerView.Adapter<AlmacenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlmacenViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        // Pasa el dbHelper al crear una instancia de AlmacenViewHolder
        return AlmacenViewHolder(layoutInflater.inflate(R.layout.item_almacen, parent, false), dbHelper)
    }

    override fun getItemCount(): Int = almacenList.size

    override fun onBindViewHolder(holder: AlmacenViewHolder, position: Int) {
        val item = almacenList[position]
        holder.render(item)
    }

    fun updateData(newData: List<Almacen>) {
        almacenList = newData
        notifyDataSetChanged()
    }

}
