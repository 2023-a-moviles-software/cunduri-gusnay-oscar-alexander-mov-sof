package com.example.examenib.models

class Almacen(
    var storeID: Int?,
    var storeName: String?,
    val storeLocation: String?,
    var products: MutableList<Electrodomestico>?,
    var isOpen: Boolean,
    var storeValue: Double
) {
    init {
        if(this.storeID == null){
            this.storeID = setId()
        }else{
            this.storeID
        }
        this.storeName
        this.storeLocation
        this.products
        this.isOpen
        this.storeValue
    }

    companion object{
        var idCounter = 0
    }

    private fun setId():Int{
       idCounter++
       return idCounter
    }
}