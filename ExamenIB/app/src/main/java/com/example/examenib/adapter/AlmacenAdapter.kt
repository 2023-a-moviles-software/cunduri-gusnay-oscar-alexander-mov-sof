package com.example.examenib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.R
import com.example.examenib.models.Almacen

class AlmacenAdapter(
    private val almacenList: List<Almacen>
) : RecyclerView.Adapter<AlmacenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlmacenViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlmacenViewHolder(layoutInflater.inflate(R.layout.item_almacen, parent, false))
    }

    override fun getItemCount(): Int = almacenList.size

    override fun onBindViewHolder(holder: AlmacenViewHolder, position: Int) {
        val item = almacenList[position]
        holder.render(item)
    }

}