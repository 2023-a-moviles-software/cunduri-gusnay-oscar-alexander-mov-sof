package com.example.exameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.exameniib.models.Almacen
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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

            crearAlmacen(nombreAlmacen, direccionAlmacen, false, almacenPrecio)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun crearAlmacen(
        nombre: String,
        direccion: String,
        isOpen: Boolean,
        valor: Double
    ) {
        val nuevoAlmacen = Almacen(
            id = null,
            storeName = nombre,
            storeLocation = direccion,
            isOpen = isOpen,
            storeValue = valor
        )

        val db = Firebase.firestore

        db.collection("almacen")
            .add(nuevoAlmacen)
            .addOnSuccessListener { }
            .addOnFailureListener { }
    }

}