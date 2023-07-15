package com.example.examenib.providers

import com.example.examenib.models.Almacen
import com.example.examenib.models.Electrodomestico

class AlmacenProvider {


    companion object {

        val almacenesList = mutableListOf<Almacen>(
            Almacen(
                null, "La ganga", "Av. Naciones Unidas", mutableListOf<Electrodomestico>(
                    Electrodomestico(null, "Celular", 500.00, 50, null, null),
                    Electrodomestico(null, "Laptop", 1500.00, 10, null, null),
                    Electrodomestico(null, "Television", 3500.00, 12, null, null)
                ), true, 50000.00
            ),
            Almacen(
                null, "El almacensito", "Av. Siempre viva", mutableListOf<Electrodomestico>(
                    Electrodomestico(null, "Celular", 500.00, 30, null, null),
                    Electrodomestico(null, "Laptop", 1200.00, 20, null, null),
                ), true, 250000.00
            )
        )
    }
}