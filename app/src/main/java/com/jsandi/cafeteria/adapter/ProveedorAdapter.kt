package com.jsandi.cafeteria.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jsandi.cafeteria.databinding.ProveedorFilaBinding
import com.jsandi.cafeteria.model.Proveedor
import com.jsandi.cafeteria.ui.home.ProveedorFragmentDirections

class ProveedorAdapter: RecyclerView.Adapter<ProveedorAdapter.ProveedorViewHolder>() {
    //lista de proveedores
    private var listaProveedores = emptyList<Proveedor>()

    fun setProveedores(proveedores: List<Proveedor>){
        listaProveedores = proveedores
        notifyDataSetChanged()
    }

    inner class ProveedorViewHolder(private val itemBinding: ProveedorFilaBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(proveedor: Proveedor){
            itemBinding.tvNombre.text = proveedor.nombre
            itemBinding.tvUbicacion.text = proveedor.ubicacion
            itemBinding.tvProximaEntrega.text = proveedor.proximaEntrega

            itemBinding.vistaFila.setOnClickListener{
                val accion = ProveedorFragmentDirections.actionNavProveedorToUpdateProveedorFragment(proveedor)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProveedorViewHolder {
        val itemBinding = ProveedorFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProveedorViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProveedorViewHolder, position: Int) {
        val lugar = listaProveedores[position]
        holder.dibuja(lugar)
    }

    override fun getItemCount(): Int {
        return listaProveedores.size
    }
}