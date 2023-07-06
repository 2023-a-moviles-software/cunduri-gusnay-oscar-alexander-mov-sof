package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ECrudEntrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenador)

        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)

        botonBuscarBDD
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val desc = findViewById<EditText>(R.id.input_desc)

                val entrenador = EBaseDeDatos.tableEntrenador!!
                    .consultarEntrenadorPorID(
                        id.text.toString().toInt()
                    )
                id.setText(entrenador.id.toString())
                nombre.setText(entrenador.nombre)
                desc.setText(entrenador.descripcion)
            }

        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)

        botonCrearBDD
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val desc = findViewById<EditText>(R.id.input_desc)

                EBaseDeDatos.tableEntrenador!!.crearEntrenador(
                    nombre.text.toString(),
                    desc.text.toString()
                )
            }

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)

        botonActualizarBDD
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val desc = findViewById<EditText>(R.id.input_desc)

                EBaseDeDatos.tableEntrenador!!.actualizarEntrenadorFormulario(
                    id.text.toString().toInt(),
                    nombre.text.toString(),
                    desc.text.toString()
                )
            }

        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)

        botonEliminarBDD
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)

                EBaseDeDatos.tableEntrenador!!.eliminarEntrenadorFormulario(
                    id.text.toString().toInt()
                )
            }
    }
}