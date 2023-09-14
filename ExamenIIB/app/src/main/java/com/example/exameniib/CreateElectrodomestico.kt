package com.example.exameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.exameniib.models.Electrodomestico
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateElectrodomestico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_electrodomestico)

        val almacenID = intent.getStringExtra("idAlmacenStr")

        val btnCrearProducto = findViewById<Button>(R.id.btn_nuevo_producto)
        val nombreProducto = findViewById<EditText>(R.id.input_create_product_nombre)
        val precioProducto = findViewById<EditText>(R.id.input_create_product_precio)
        val cantidadProducto = findViewById<EditText>(R.id.input_create_product_cantidad)

        btnCrearProducto.setOnClickListener {

            val nombreElectro = nombreProducto.text.toString()
            val precioElectro = precioProducto.text.toString().toDouble()
            val cantidadElectro = cantidadProducto.text.toString().toInt()

            crearElectrodomestico(nombreElectro, precioElectro, cantidadElectro, almacenID)

            val intent = Intent(this, ListaProductos::class.java)
            intent.putExtra("idAProducto", almacenID)
            startActivity(intent)
        }
    }

    private fun crearElectrodomestico(
        nombre: String,
        precio: Double,
        cantidad: Int,
        almacenId: String?
    ) {
            val nuevoElectrodomestico = Electrodomestico(
                id = null,
                productName = nombre,
                productType = nombre[0].toString().toUpperCase(),
                price = precio,
                cantidad = cantidad,
                almacenId = almacenId
            )

            val db = Firebase.firestore

            db.collection("electrodomesticos")
                .add(nuevoElectrodomestico)
                .addOnSuccessListener { }
                .addOnFailureListener { }

    }
}