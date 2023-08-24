package com.example.movilessoftware2023a

class ICities(
    public var name: String?,
    public var state: String?,
    public var country: String?,
    public var capital: Boolean?,
    public var population: Long?,
    public var regiones: ArrayList<String>?
) {
    override fun toString(): String {
        return "${name} - ${country}"
    }
}