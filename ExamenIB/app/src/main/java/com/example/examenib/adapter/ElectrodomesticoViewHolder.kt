package com.example.examenib.adapter

import android.view.TextureView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.R
import com.example.examenib.models.Electrodomestico

class ElectrodomesticoViewHolder (view: View): RecyclerView.ViewHolder(view){

    val txtIdProducto = view.findViewById<TextView>(R.id.txt_electrodomestico_id)
    val txtNombreProducto = view.findViewById<TextView>(R.id.txt_nombre_producto)
    val txtCantidad = view.findViewById<TextView>(R.id.txt_cantidad)
    val txtprecio = view.findViewById<TextView>(R.id.txt_precio_producto)
    val txtCategoria = view.findViewById<TextView>(R.id.txt_categoria)

    fun render(electrodomestico: Electrodomestico){
        txtIdProducto.text = electrodomestico.productID.toString()
        txtNombreProducto.text = electrodomestico.productName
        txtCantidad.text = electrodomestico.cantidad.toString()
        txtprecio.text = electrodomestico.price.toString()
        txtCategoria.text = electrodomestico.productType.toString()

    }
}