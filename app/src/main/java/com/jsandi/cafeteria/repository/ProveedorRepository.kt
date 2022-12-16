package com.jsandi.cafeteria.repository

import androidx.lifecycle.MutableLiveData
import com.jsandi.cafeteria.data.ProveedorDao
import com.jsandi.cafeteria.model.Proveedor

class ProveedorRepository (private val proveedorDao: ProveedorDao) {
    fun guardarProveedor(proveedor: Proveedor){
        proveedorDao.guardarProveedor(proveedor)
    }

    fun eliminarProveedor(id: String){
        proveedorDao.eliminarProveedor(id)
    }

    val getProveedores: MutableLiveData<List<Proveedor>> = proveedorDao.getProovedores()
}