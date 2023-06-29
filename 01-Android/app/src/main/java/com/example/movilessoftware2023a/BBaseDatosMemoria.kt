package com.example.movilessoftware2023a

class BBaseDatosMemoria {

    companion object {
        val arregloBEntrnador = arrayListOf<Bentrenador>()

        init {
            arregloBEntrnador.add(
                Bentrenador(1, "Oscar", "Desc oscar")
            )
            arregloBEntrnador.add(
                Bentrenador(2, "Juan", "Desc juan")
            )
            arregloBEntrnador.add(
                Bentrenador(3, "Pepe", "Desc pepe")
            )
        }
    }
}