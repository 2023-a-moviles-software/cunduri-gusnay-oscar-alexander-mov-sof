package com.example.examenib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CreateAlmacen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_almacen)

        val txtNombreAlmacen = findViewById<EditText>(R.id.input_nombre)
        val txtDireccionAlmacen = findViewById<EditText>(R.id.input_direccion)
        val txtAlmacenPrecio = findViewById<EditText>(R.id.input_precio)

        val botonCrearAlmacen = findViewById<Button>(R.id.btn_crear_nuevo_almacen)

        botonCrearAlmacen.setOnClickListener {

            val nombreAlmacen = txtNombreAlmacen.text.toString()
            val direccionAlmacen = txtDireccionAlmacen.text.toString()
            val almacenPrecio = txtAlmacenPrecio.text.toString().toDouble()

            val intent = Intent()
            intent.putExtra("nombre", nombreAlmacen)
            intent.putExtra("direccion", direccionAlmacen)
            intent.putExtra("precio", almacenPrecio)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}
