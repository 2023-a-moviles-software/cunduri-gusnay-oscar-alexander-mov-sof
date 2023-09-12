package com.example.exameniib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Llama a una función para agregar datos de prueba
        agregarDatosDePrueba()
    }

    private fun agregarDatosDePrueba() {
        // Accede a la colección "almacenes" en Firestore
        val almacenRef = db.collection("almacen")

        // Crea un nuevo mapa (documento) con datos de prueba
        val nuevoAlmacen = hashMapOf(
            "storeName" to "Mi Almacén",
            "storeLocation" to "Av 12 de octubre",
            "isOpen" to false,
            "storeValue" to 50000.0
        )

        // Agrega el nuevo documento a la colección
        almacenRef
            .add(nuevoAlmacen)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Documento agregado con ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al agregar documento", e)
            }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}





