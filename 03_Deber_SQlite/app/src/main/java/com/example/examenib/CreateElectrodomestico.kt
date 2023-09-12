package com.example.examenib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examenib.adapter.ElectrodomesticoAdapter
import com.example.examenib.models.Electrodomestico
import com.example.examenib.providers.AlmacenProvider

class CreateElectrodomestico : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_electrodomestico)

        val btnCrearProducto = findViewById<Button>(R.id.btn_nuevo_producto)
        val nombreProducto = findViewById<EditText>(R.id.input_create_product_nombre)
        val precioProducto = findViewById<EditText>(R.id.input_create_product_precio)
        val cantidadProducto = findViewById<EditText>(R.id.input_create_product_cantidad)

        btnCrearProducto.setOnClickListener {

            val nombreElectro = nombreProducto.text.toString()
            val precioElectro = precioProducto.text.toString()
            val cantidadElectro = cantidadProducto.text.toString()


            val intent = Intent()
            intent.putExtra("nombreE", nombreElectro)
            intent.putExtra("precioE", precioElectro)
            intent.putExtra("CantidadE", cantidadElectro)

            setResult(RESULT_OK, intent)
            finish()

        }
    }
}