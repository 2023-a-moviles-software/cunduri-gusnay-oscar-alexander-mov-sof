package com.example.movilessoftware2023a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHeplerEntrenador(
    contexto: Context?,
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLcrearTablaEntrenador = """
                CREATE TABLE ENTRENADOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    descripcion VARCHAR(50)
                )
             """.trimIndent()

        db?.execSQL(scriptSQLcrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ): Boolean {
        val baseDatosEscritura = writableDatabase
        val valoresAguardar = ContentValues()
        valoresAguardar.put("nombre", nombre)
        valoresAguardar.put("descripcion", descripcion)
        val resultadoGuardar = baseDatosEscritura.insert(
            "ENTRENADOR",
            null,
            valoresAguardar
        )
        baseDatosEscritura.close()
        return if (resultadoGuardar.toInt() === -1) false else true
    }

    fun eliminarEntrenadorFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",//tabla
                "id=?",//consulta
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion == -1) false else true

    }

    fun actualizarEntrenadorFormulario(id: Int, nombre: String, descripcion: String): Boolean {

        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)

        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "ENTRENADOR",//tabla
                valoresAActualizar,
                "id=?",//consulta
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true

    }

    fun consultarEntrenadorPorID(id: Int): Bentrenador {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura =
            """ SELECT * FROM ENTRENADOR WHERE ID = ? """.trimIndent()

        val parametrosConsultaActualizar = arrayOf(id.toString())

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaActualizar
        )

        //LOGICA DE BUSQUEDA
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = Bentrenador(0, "", "")

        val arreglo = arrayListOf<Bentrenador>()

        if (existeUsuario) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)
                if (id != null) {
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
}


