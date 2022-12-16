package com.jsandi.cafeteria.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.jsandi.cafeteria.model.Producto

class ProductoDao {
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init{
        codigoUsuario = Firebase.auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getProductos(): MutableLiveData<List<Producto>> {
        val listaProductos = MutableLiveData<List<Producto>>()
        firestore
            .collection("productos")
            .document(codigoUsuario)
            .collection("misProductos")
            .addSnapshotListener { value, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                if(value != null){
                    val lista = ArrayList<Producto>()
                    val productos = value.documents
                    productos.forEach{
                        val producto = it.toObject(Producto::class.java)
                        if(producto != null){
                            lista.add(producto)
                        }
                    }
                    listaProductos.value = lista
                }
            }
        return listaProductos
    }

    fun guardarProducto(producto: Producto){
        val document: DocumentReference
        if(producto.id.isEmpty()){
            //Agregar
            document = firestore
                .collection("productos")
                .document(codigoUsuario)
                .collection("misProductos")
                .document()
            producto.id = document.id
        }
        else{
            //Modificar
            document = firestore
                .collection("productos")
                .document(codigoUsuario)
                .collection("misProductos")
                .document(producto.id)
        }
        document.set(producto)
            .addOnSuccessListener {
                Log.d("guardarProducto", "Guardado con exito")
            }
            .addOnFailureListener{
                Log.e("guardarProducto", "Error al guardar un producto")
            }
    }

    fun eliminarProducto(id: String){
        if(id.isNotEmpty()){
            firestore
                .collection("productos")
                .document(codigoUsuario)
                .collection("misProductos")
                .document(id)
                .delete()
                .addOnSuccessListener{
                    Log.d("eliminarProducto", "Eliminado con exito")
                }
                .addOnFailureListener{
                    Log.e("guardarProducto", "Error al eliminar un producto")
                }
        }
    }
}