package com.jsandi.cafeteria.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jsandi.cafeteria.data.PedidoDao
import com.jsandi.cafeteria.model.Pedido
import com.jsandi.cafeteria.repository.PedidoRepository

class PedidosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PedidoRepository = PedidoRepository(PedidoDao())

    val obtenerPedidos: MutableLiveData<List<Pedido>>

    init{
        obtenerPedidos = repository.getPedidos
    }

    fun guardarPedido(pedido: Pedido){
        repository.guardarPedido(pedido)
    }

    fun eliminarPedido(id: String){
        repository.eliminarPedido(id)
    }
}