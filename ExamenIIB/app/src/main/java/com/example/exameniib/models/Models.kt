package com.example.exameniib.models

data class Almacen(
    val id: String?,
    val storeName: String?,
    val storeLocation: String?,
    val isOpen: Boolean?,
    val storeValue: Double?
)

data class Electrodomestico(
    val id: String?,
    val productName: String,
    val price: Double,
    val cantidad: Int,
    val almacenId: String,
)