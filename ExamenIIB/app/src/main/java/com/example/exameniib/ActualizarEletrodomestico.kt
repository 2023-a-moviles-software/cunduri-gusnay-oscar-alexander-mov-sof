package com.example.exameniib

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarEletrodomestico : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_eletrodomestico)


        val idElectro = intent.getStringExtra("idProducto")
        val idAlmacen = intent.getStringExtra("idAlmacen")

        val txtNombre = findViewById<EditText>(R.id.txt_actualizar_nombre_input)
        val txtPrecio = findViewById<EditText>(R.id.txt_actualizar_precio_input)
        val txtCantidad = findViewById<EditText>(R.id.txt_actualizar_cantidad_input)
        val btnActualizar = findViewById<Button>(R.id.guardar_producto_actualizado)

        Log.i("RECIBE ID electro?", "Esta es la id que recibe ${idElectro}")

        val db = Firebase.firestore
        val electroRef = idElectro
            ?.let {
                db.collection("electrodomesticos")
                    .document(it)
            }

        electroRef?.get()
            ?.addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val electroData = documentSnapshot.data
                    if (electroData != null) {
                        val nombre = electroData["productName"] as? String
                        val precio = electroData["price"] as? Double

                        if (nombre != null && precio != null) {
                            txtNombre.setText(nombre)
                            txtPrecio.setText(precio.toString())
                            txtCantidad.setText("Cantidad")
                        }
                    }
                } else {
                    Log.e("Electrodomestico", "El documento no existe.")
                }
            }
            ?.addOnFailureListener { e ->
                Log.e("Electrodomestico", "Error al obtener el documento: $e")
            }

        btnActualizar.setOnClickListener {
            if (idElectro != null) {
                val db1 = Firebase.firestore
                val almacenRef1 = db1.collection("electrodomesticos").document(idElectro)
                val nuevoNombre = txtNombre.text.toString()
                val nuevoPrecio = txtPrecio.text.toString().toDouble()
                val cantidad = txtCantidad.text.toString().toInt()

                val nuevosDatos = hashMapOf(
                    "productName" to nuevoNombre,
                    "price" to nuevoPrecio,
                    "cantidad" to cantidad
                )

                almacenRef1.update(nuevosDatos as Map<String, Any>)
                    .addOnSuccessListener {
                        val intent = Intent(this, ListaProductos::class.java)
                        intent.putExtra("idAProducto", idAlmacen)
                        startActivity(intent)
                    }
                    .addOnFailureListener { }
            }
        }
    }
}
