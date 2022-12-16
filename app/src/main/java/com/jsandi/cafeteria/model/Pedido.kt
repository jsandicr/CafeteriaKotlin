package com.jsandi.cafeteria.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pedido(
    var id: String,
    val descripcionPedido: String,
    val nombreCliente: String?,
    val estado: String?
) : Parcelable {
    constructor():
        this("", "", "", "")
}