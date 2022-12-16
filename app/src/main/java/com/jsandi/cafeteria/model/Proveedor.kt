package com.jsandi.cafeteria.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Proveedor(
    var id: String,
    val nombre: String,
    val ubicacion: String?,
    val proximaEntrega: String?
) : Parcelable {
    constructor():
        this("", "", "", "")
}