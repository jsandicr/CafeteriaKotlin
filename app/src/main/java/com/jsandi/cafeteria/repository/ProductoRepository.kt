package com.jsandi.cafeteria.repository

import androidx.lifecycle.MutableLiveData
import com.jsandi.cafeteria.data.ProductoDao
import com.jsandi.cafeteria.model.Producto

class ProductoRepository (private val productoDao: ProductoDao) {
    fun guardarProducto(producto: Producto){
        productoDao.guardarProducto(producto)
    }

    fun eliminarProducto(id: String){
        productoDao.eliminarProducto(id)
    }

    val getProductos: MutableLiveData<List<Producto>> = productoDao.getProductos()
}