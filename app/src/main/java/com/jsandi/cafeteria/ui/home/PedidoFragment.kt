package com.jsandi.cafeteria.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jsandi.cafeteria.R.id.action_nav_pedido_to_addPedidoFragment
import com.jsandi.cafeteria.adapter.PedidoAdapter
import com.jsandi.cafeteria.databinding.FragmentPedidoBinding
import com.jsandi.cafeteria.viewModel.PedidosViewModel

class PedidoFragment : Fragment() {

    private var _binding: FragmentPedidoBinding? = null

    private val binding get() = _binding!!
    private lateinit var pedidosViewModel: PedidosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pedidosViewModel = ViewModelProvider(this).get(PedidosViewModel::class.java)
        _binding = FragmentPedidoBinding.inflate(inflater, container, false)

        binding.btAddPedido.setOnClickListener{
            findNavController().navigate(action_nav_pedido_to_addPedidoFragment)
        }

        val pedidoAdapter = PedidoAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = pedidoAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        pedidosViewModel.obtenerPedidos.observe(viewLifecycleOwner){
            pedidos -> pedidoAdapter.setPedidos(pedidos)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}