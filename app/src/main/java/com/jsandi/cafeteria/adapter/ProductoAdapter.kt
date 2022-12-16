package com.jsandi.cafeteria.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jsandi.cafeteria.databinding.ProductoFilaBinding
import com.jsandi.cafeteria.model.Producto
import com.jsandi.cafeteria.ui.home.HomeFragmentDirections

class ProductoAdapter: RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {
    //lista de productos
    private var listaProductos = emptyList<Producto>()

    fun setProductos(productos: List<Producto>){
        listaProductos = productos
        notifyDataSetChanged()
    }

    inner class ProductoViewHolder(private val itemBinding: ProductoFilaBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(producto: Producto){
            itemBinding.tvNombre.text = producto.nombre
            itemBinding.tvPrecio.text = producto.precio
            itemBinding.tvStock.text = producto.stock

            itemBinding.vistaFila.setOnClickListener{
                val accion = HomeFragmentDirections.actionNavHomeToUpdateProductoFragment(producto)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val itemBinding = ProductoFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val lugar = listaProductos[position]
        holder.dibuja(lugar)
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }
}