package com.jsandi.cafeteria.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.jsandi.cafeteria.model.Pedido

class PedidoDao {
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init{
        codigoUsuario = Firebase.auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getPedidos(): MutableLiveData<List<Pedido>> {
        val listaPedidos = MutableLiveData<List<Pedido>>()
        firestore
            .collection("pedidos")
            .document(codigoUsuario)
            .collection("misPedidos")
            .addSnapshotListener { value, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                if(value != null){
                    val lista = ArrayList<Pedido>()
                    val pedidos = value.documents
                    pedidos.forEach{
                        val pedido = it.toObject(Pedido::class.java)
                        if(pedido != null){
                            lista.add(pedido)
                        }
                    }
                    listaPedidos.value = lista
                }
            }
        return listaPedidos
    }

    fun guardarPedido(pedido: Pedido){
        val document: DocumentReference
        if(pedido.id.isEmpty()){
            //Agregar
            document = firestore
                .collection("pedidos")
                .document(codigoUsuario)
                .collection("misPedidos")
                .document()
            pedido.id = document.id
        }
        else{
            //Modificar
            document = firestore
                .collection("pedidos")
                .document(codigoUsuario)
                .collection("misPedidos")
                .document(pedido.id)
        }
        document.set(pedido)
            .addOnSuccessListener {
                Log.d("guardarPedido", "Guardado con exito")
            }
            .addOnFailureListener{
                Log.e("guardarPedido", "Error al guardar un pedido")
            }
    }

    fun eliminarPedido(id: String){
        if(id.isNotEmpty()){
            firestore
                .collection("pedidos")
                .document(codigoUsuario)
                .collection("misPedidos")
                .document(id)
                .delete()
                .addOnSuccessListener{
                    Log.d("eliminarPedido", "Eliminado con exito")
                }
                .addOnFailureListener{
                    Log.e("guardarPedido", "Error al eliminar un pedido")
                }
        }
    }
}