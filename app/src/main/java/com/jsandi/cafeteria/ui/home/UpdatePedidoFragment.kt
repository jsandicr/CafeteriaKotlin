package com.jsandi.cafeteria.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jsandi.cafeteria.R
import com.jsandi.cafeteria.databinding.FragmentUpdatePedidoBinding
import com.jsandi.cafeteria.model.Pedido
import com.jsandi.cafeteria.viewModel.PedidosViewModel

class UpdatePedidoFragment : Fragment() {

    private val args by navArgs<UpdatePedidoFragmentArgs>()

    private var _binding: FragmentUpdatePedidoBinding? = null
    private val binding get() = _binding!!
    private lateinit var pedidosViewModel: PedidosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pedidosViewModel = ViewModelProvider(this).get(PedidosViewModel::class.java)
        _binding = FragmentUpdatePedidoBinding.inflate(inflater, container, false)

        binding.etDescripcionPedido.setText(args.pedidoArg.descripcionPedido)
        binding.etNombreCliente.setText(args.pedidoArg.nombreCliente)
        binding.etEstado.setText(args.pedidoArg.estado)

        binding.btUpdatePedido.setOnClickListener{ updatePedido() }
        binding.btDeletePedido.setOnClickListener{ deletePedido() }

        return binding.root
    }

    private fun updatePedido(){
        val descripcionPedido = binding.etDescripcionPedido.text.toString()
        val nombreCliente = binding.etNombreCliente.text.toString()
        val estado = binding.etEstado.text.toString()
        if(descripcionPedido.isNotEmpty()){
            val pedido = Pedido(args.pedidoArg.id, descripcionPedido, nombreCliente, estado)
            pedidosViewModel.guardarPedido(pedido)
            Toast.makeText(requireContext(),getString(R.string.msg_UpdatePedido_correcto), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updatePedidoFragment_to_nav_pedido)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_AddPedido_sinNombre), Toast.LENGTH_LONG).show()
        }
    }

    private fun deletePedido(){
        pedidosViewModel.eliminarPedido(args.pedidoArg.id)
        Toast.makeText(requireContext(),getString(R.string.msg_DeletePedido_correcto), Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updatePedidoFragment_to_nav_pedido)
    }
}