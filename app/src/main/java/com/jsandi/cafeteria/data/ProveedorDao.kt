package com.jsandi.cafeteria.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.jsandi.cafeteria.model.Producto
import com.jsandi.cafeteria.model.Proveedor

class ProveedorDao {
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init{
        codigoUsuario = Firebase.auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getProovedores(): MutableLiveData<List<Proveedor>> {
        val listaProveedores = MutableLiveData<List<Proveedor>>()
        firestore
            .collection("proveedores")
            .addSnapshotListener { value, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                if(value != null){
                    val lista = ArrayList<Proveedor>()
                    val proveedores = value.documents
                    proveedores.forEach{
                        val proveedor = it.toObject(Proveedor::class.java)
                        if(proveedor != null){
                            lista.add(proveedor)
                        }
                    }
                    listaProveedores.value = lista
                }
            }
        return listaProveedores
    }

    fun guardarProveedor(proveedor: Proveedor){
        val document: DocumentReference
        if(proveedor.id.isEmpty()){
            //Agregar
            document = firestore
                .collection("proveedores")
                .document()
            proveedor.id = document.id
        }
        else{
            //Modificar
            document = firestore
                .collection("proveedores")
                .document(proveedor.id)
        }
        document.set(proveedor)
            .addOnSuccessListener {
                Log.d("guardarProveedor", "Guardado con exito")
            }
            .addOnFailureListener{
                Log.e("guardarProveedor", "Error al guardar un proveedor")
            }
    }

    fun eliminarProveedor(id: String){
        if(id.isNotEmpty()){
            firestore
                .collection("proveedores")
                .document(id)
                .delete()
                .addOnSuccessListener{
                    Log.d("eliminarProveedor", "Eliminado con exito")
                }
                .addOnFailureListener{
                    Log.e("guardarProveedor", "Error al eliminar un proveedor")
                }
        }
    }
}