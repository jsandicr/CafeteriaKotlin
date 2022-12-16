package com.jsandi.cafeteria.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jsandi.cafeteria.data.ProveedorDao
import com.jsandi.cafeteria.model.Proveedor
import com.jsandi.cafeteria.repository.ProveedorRepository

class ProveedorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProveedorRepository = ProveedorRepository(ProveedorDao())

    val obtenerProveedores: MutableLiveData<List<Proveedor>>

    init{
        obtenerProveedores = repository.getProveedores
    }

    fun guardarProveedor(proveedor: Proveedor){
        repository.guardarProveedor(proveedor)
    }

    fun eliminarProveedor(id: String){
        repository.eliminarProveedor(id)
    }
}