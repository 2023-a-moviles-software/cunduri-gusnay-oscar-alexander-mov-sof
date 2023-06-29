package com.example.movilessoftware2023a

class Bentrenador(var id: Int, var nombre: String?, var descripcion: String?) {

    override fun toString(): String {
        return "${id}, ${nombre}, ${descripcion}"
    }

}