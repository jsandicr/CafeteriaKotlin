package com.jsandi.cafeteria.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.jsandi.cafeteria.data.ProductoDao
import com.jsandi.cafeteria.model.Producto
import com.jsandi.cafeteria.repository.ProductoRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductoRepository = ProductoRepository(ProductoDao())
    val obtenerProductos: MutableLiveData<List<Producto>>

    init{
        obtenerProductos = repository.getProductos
    }

    fun guardarProducto(producto: Producto){
        repository.guardarProducto(producto)
    }

    fun eliminarProducto(id: String){
        repository.eliminarProducto(id)
    }
}