package com.example.examenib.models

class Almacen(
    val storeID: Int?,
    val storeName: String?,
    val storeLocation: String?,
    val products: List<Electrodomestico>?,
    val isOpen: Boolean,
    val storeValue: Double
) {
    init {
        this.storeID
        this.storeName
        this.storeLocation
        this.products
        this.isOpen
        this.storeValue
    }
}