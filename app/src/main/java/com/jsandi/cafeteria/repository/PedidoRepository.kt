package com.jsandi.cafeteria.repository

import androidx.lifecycle.MutableLiveData
import com.jsandi.cafeteria.data.PedidoDao
import com.jsandi.cafeteria.model.Pedido

class PedidoRepository (private val pedidoDao: PedidoDao) {
    fun guardarPedido(pedido: Pedido){
        pedidoDao.guardarPedido(pedido)
    }

    fun eliminarPedido(id: String){
        pedidoDao.eliminarPedido(id)
    }

    val getPedidos: MutableLiveData<List<Pedido>> = pedidoDao.getPedidos()
}