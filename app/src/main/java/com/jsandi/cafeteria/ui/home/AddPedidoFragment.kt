package com.jsandi.cafeteria.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jsandi.cafeteria.R
import com.jsandi.cafeteria.databinding.FragmentAddPedidoBinding
import com.jsandi.cafeteria.model.Pedido
import com.jsandi.cafeteria.viewModel.PedidosViewModel


class AddPedidoFragment : Fragment() {

    private var _binding: FragmentAddPedidoBinding? = null
    private val binding get() = _binding!!
    private lateinit var pedidosViewModel: PedidosViewModel

    override fun onCreateView(
         inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pedidosViewModel = ViewModelProvider(this).get(PedidosViewModel::class.java)
        _binding = FragmentAddPedidoBinding.inflate(inflater,container,false)

        binding.addPedido.setOnClickListener{
            agregarPedido()
        }

        return binding.root
    }

    private fun agregarPedido(){
        val descripcionPedido = binding.etDescripcionPedido.text.toString()
        val nombreCliente = binding.etNombreCliente.text.toString()
        val estado = binding.etEstado.text.toString()

        if(descripcionPedido.isNotEmpty()){
            var pedido = Pedido("", descripcionPedido, nombreCliente, estado)
            pedidosViewModel.guardarPedido(pedido)
            Toast.makeText(requireContext(), getText(R.string.msg_AddPedido_correcto), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addPedidoFragment_to_nav_pedido)
        }else{
            Toast.makeText(requireContext(), getText(R.string.msg_AddPedido_sinNombre), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}