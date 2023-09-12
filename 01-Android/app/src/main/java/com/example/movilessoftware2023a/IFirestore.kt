package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date

class IFirestore : AppCompatActivity() {

    var query: Query? = null
    val arreglo: ArrayList<ICities> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirestore)

        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        //BTONOES

        val botonDatoPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatoPrueba.setOnClickListener { crearDatosPrueba() }

        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener { consultarConOrderBy(adaptador) }

        val botonObtenerUnDocumento = findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerUnDocumento.setOnClickListener { consultarDocumento(adaptador) }

        val botoneIndiceCompuesto = findViewById<Button>(R.id.btn_fs_ind_comp)
        botoneIndiceCompuesto.setOnClickListener { consultarIndiceCompuesto(adaptador) }


        val botonCrear = findViewById<Button>(R.id.btn_fs_crear)
        botonCrear.setOnClickListener { crearEjemplo() }

        val botonEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonEliminar.setOnClickListener { eliminar() }

        //EMPEZAR A PAGINAR

        val botonEmpezarPaginar = findViewById<Button>(R.id.btn_fs_epaginar)
        botonEmpezarPaginar.setOnClickListener {
            query = null; consultarCiudades(adaptador)
        }

        val botonPaginar = findViewById<Button>(R.id.btn_fs_paginar)
        botonPaginar.setOnClickListener { consultarCiudades(adaptador) }

    }

    private fun consultarCiudades(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore
        val citiesRef = db.collection("cities")
            .orderBy("population")
            .limit(1)
        var tarea: Task<QuerySnapshot>? = null
        if (query == null) {
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
            tarea = citiesRef.get()
        } else {
            tarea = query!!.get()
        }
        if (tarea != null) {
            tarea
                .addOnSuccessListener { documentSnapshots ->
                    guardarQuery(documentSnapshots, citiesRef)
                    for (ciudad in documentSnapshots) {
                        anadirArregloCiudad(ciudad)
                    }
                    adaptador.notifyDataSetChanged()
                }
                .addOnFailureListener { }
        }
    }

    private fun guardarQuery(
        documentSnapshot: QuerySnapshot,
        refCities: Query
    ) {
        if (documentSnapshot.size() > 0) {
            val ultimoDocumento = documentSnapshot
                .documents[documentSnapshot.size() - 1]
            query = refCities
                .startAfter(ultimoDocumento)
        }
    }

    private fun eliminar() {
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db.collection("ejemplo")
        referenciaEjemploEstudiante
            .document("12345678")
            .delete()
            .addOnSuccessListener { }
            .addOnFailureListener { }
    }

    private fun crearEjemplo() {
        val db = Firebase.firestore
        val refenciaEjemploEstudiante = db.collection("ejemplo")
        val identificador = Date().time
        val datosEstudiante = hashMapOf(
            "nombre" to "Oscar",
            "graduado" to false,
            "promedio" to 14.00,
            "direccion" to hashMapOf(
                "direccion" to "Caupicho",
                "numeroCalle" to "E4C"
            ),
            "materias" to listOf("web", "moviles", "VyV")
        )
        //identificador quemado (CREAR - ACTUALIZAR)
        refenciaEjemploEstudiante
            .document("12345678")
            .set(datosEstudiante)
            .addOnSuccessListener { }
            .addOnFailureListener { }
        //identficiador quemado pero autogenerado con el Date.time CREAR - ACTUALIZAR
        refenciaEjemploEstudiante
            .document(identificador.toString())
            .set(datosEstudiante)
            .addOnSuccessListener { }
            .addOnFailureListener { }
        //SIN IDENTIFICADOR  CREAR
        refenciaEjemploEstudiante
            .add(datosEstudiante)
            .addOnSuccessListener { }
            .addOnFailureListener { }
    }

    private fun consultarIndiceCompuesto(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore
        val citiresRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiresRefUnico
            .whereEqualTo("capital", false)
            .whereLessThanOrEqualTo("population", 4000000)
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it) {
                    anadirArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener { }
    }

    fun consultarDocumento(
        adaptador: ArrayAdapter<ICities>
    ) {
        val db = Firebase.firestore
        val citiesRegUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()

        citiesRegUnico
            .document("BJ")
            .get() // obtiene un solo documento
            .addOnSuccessListener { //it es un objeto, ya no hay arreglo
                arreglo
                    .add(
                        ICities(
                            it.data?.get("name") as String?,
                            it.data?.get("state") as String?,
                            it.data?.get("country") as String?,
                            it.data?.get("capital") as Boolean?,
                            it.data?.get("pupulation") as Long?,
                            it.data?.get("regions") as ArrayList<String>?
                        )
                    )
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener { }

    }

    private fun consultarConOrderBy(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore

        val citiesRfUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRfUnico
            .orderBy("population", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it) {
                    ciudad.id
                    anadirArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                //ERRORES
            }
    }

    private fun limpiarArreglo() {
        arreglo.clear()
    }

    private fun anadirArregloCiudad(
        ciudad: QueryDocumentSnapshot
    ) {
        val nuevaCiudad = ICities(
            ciudad.data.get("name") as String?,
            ciudad.data.get("state") as String?,
            ciudad.data.get("country") as String?,
            ciudad.data.get("capital") as Boolean?,
            ciudad.data.get("pupulation") as Long?,
            ciudad.data.get("regions") as ArrayList<String>?
        )
        arreglo.add(nuevaCiudad)
    }

    private fun crearDatosPrueba() {
        val db = Firebase.firestore

        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal"),
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal"),
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast"),
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu"),
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei"),
        )
        cities.document("BJ").set(data5)

    }


}