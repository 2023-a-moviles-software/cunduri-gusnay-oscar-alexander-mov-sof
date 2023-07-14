package com.example.examenib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ListaProductos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_productos)

        val idAlmacen = intent.getStringExtra("id")
    }
}