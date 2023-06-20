package models.dto

class Almacen(
    val storeID: Int,
    val storeName: String,
    val storeLocation: String,
    val products: List<Electrodomesticos>,
    val isOpen: Boolean,
    val storeValue: Double
) {
    init {
        this.storeName
        this.storeLocation
        this.storeID
        this.products
        this.isOpen
        this.storeValue
    }
}