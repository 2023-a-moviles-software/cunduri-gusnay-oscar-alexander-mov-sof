package com.example.examenib.providers

import com.example.examenib.models.Almacen
import com.example.examenib.models.Electrodomestico

class AlmacenProvider {


    companion object {

        val almacenesList = listOf<Almacen>(
            Almacen(
                1, "La ganga", "Av. Naciones Unidas", listOf<Electrodomestico>(
                    Electrodomestico(1, "Celular", 500.00, 50, null, null),
                    Electrodomestico(2, "Laptop", 1500.00, 10, null, null),
                    Electrodomestico(3, "Television", 3500.00, 12, null, null)
                ), true, 50000.00
            ),
            Almacen(
                2, "El almacensito", "Av. Siempre viva", listOf<Electrodomestico>(
                    Electrodomestico(1, "Celular", 500.00, 30, null, null),
                    Electrodomestico(2, "Laptop", 1200.00, 20, null, null),
                ), true, 250000.00
            )
        )
    }
}