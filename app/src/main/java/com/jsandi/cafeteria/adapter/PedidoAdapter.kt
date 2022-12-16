package com.jsandi.cafeteria.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jsandi.cafeteria.databinding.PedidoFilaBinding
import com.jsandi.cafeteria.model.Pedido
import com.jsandi.cafeteria.ui.home.HomeFragmentDirections
import com.jsandi.cafeteria.ui.home.PedidoFragmentDirections
import com.jsandi.cafeteria.ui.home.PedidoFragmentDirections.Companion.actionNavPedidoToUpdatePedidoFragment

class PedidoAdapter: RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {
    //lista de pedidos
    private var listaPedidos = emptyList<Pedido>()

    fun setPedidos(pedidos: List<Pedido>){
        listaPedidos = pedidos
        notifyDataSetChanged()
    }

    inner class PedidoViewHolder(private val itemBinding: PedidoFilaBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(pedido: Pedido){
            itemBinding.tvDescripcionPedido.text = pedido.descripcionPedido
            itemBinding.tvNombreCliente.text = pedido.nombreCliente
            itemBinding.tvEstado.text = pedido.estado

            itemBinding.vistaFila.setOnClickListener{
                val accion = PedidoFragmentDirections.actionNavPedidoToUpdatePedidoFragment(pedido)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val itemBinding = PedidoFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PedidoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val lugar = listaPedidos[position]
        holder.dibuja(lugar)
    }

    override fun getItemCount(): Int {
        return listaPedidos.size
    }
}