package com.example.exameniib.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exameniib.R
import com.example.exameniib.models.Almacen
import com.example.exameniib.viewholders.AlmacenViewHolder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AlmacenAdapter(
    private var almacenList: MutableList<Almacen>,
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

    fun updateData(newData: MutableList<Almacen>) {
        almacenList = newData
        notifyDataSetChanged()
    }

    fun clearData() {
        almacenList.clear()
        notifyDataSetChanged()
    }

}