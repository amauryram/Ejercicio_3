package com.midominio.ejercicio_3.model

import com.google.gson.annotations.SerializedName

class ProductoDetalles {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("imag_url")
    var imag_url: String? = null

    @SerializedName("desc")
    var desc: String? = null

}