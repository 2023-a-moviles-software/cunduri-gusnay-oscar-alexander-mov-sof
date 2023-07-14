package com.example.examenib.models

class Electrodomestico(
    val productID: Int?,
    val productName: String,
    val price: Double,
    val cantidad: Int,
    var productType: Char?,
    var isAvailable: Boolean?,
) {

    init {
        this.productID
        this.productName
        this.price
        this.cantidad
        this.productType = this.productName[0]
        this.isAvailable = this.cantidad > 0
    }


}