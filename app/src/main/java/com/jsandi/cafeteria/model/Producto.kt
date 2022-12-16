package com.jsandi.cafeteria.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Producto(
    var id: String,
    val nombre: String,
    val precio: String?,
    val stock: String?,
    //val rutaImagen: String?
) : Parcelable {
    constructor():
        this("", "", "", "")
}