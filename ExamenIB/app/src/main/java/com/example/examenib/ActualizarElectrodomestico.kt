package com.example.examenib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examenib.adapter.AlmacenAdapter
import com.example.examenib.adapter.ElectrodomesticoAdapter
import com.example.examenib.providers.AlmacenProvider

class ActualizarElectrodomestico : AppCompatActivity() {
    companion object {
        private lateinit var adapter: ElectrodomesticoAdapter

        fun setAdapter(adapterE: ElectrodomesticoAdapter) {
            adapter = adapterE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_electrodomestico)

        val idAlmacen = intent.getStringExtra("idA")?.toInt()
        val idElectro = intent.getStringExtra("idE")?.toInt()


        val almacen = AlmacenProvider.almacenesList.find { almacen -> almacen.storeID == idAlmacen }


        val electrodomestico =
            almacen?.products?.find { producto -> producto.productID == idElectro }


        val txtNombre = findViewById<EditText>(R.id.txt_actualizar_nombre_input)
        val txtPrecio = findViewById<EditText>(R.id.txt_actualizar_precio_input)
        val txtCantidad = findViewById<EditText>(R.id.txt_actualizar_cantidad_input)

        if (electrodomestico != null) {
            txtNombre.setText(electrodomestico.productName)
            txtPrecio.setText(electrodomestico.price.toString())
            txtCantidad.setText(electrodomestico.cantidad.toString())
        }

        val btnActualizar = findViewById<Button>(R.id.guardar_producto_actualizado)

        btnActualizar.setOnClickListener {
            if (electrodomestico != null) {
                electrodomestico.productName = txtNombre.text.toString()
                electrodomestico.price = txtPrecio.text.toString().toDouble()
                electrodomestico.cantidad = txtCantidad.text.toString().toInt()
                adapter.notifyDataSetChanged()
                finish()
            }
        }

    }
}