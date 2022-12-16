package com.jsandi.cafeteria.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jsandi.cafeteria.R.id.action_nav_proveedor_to_addProveedorFragment
import com.jsandi.cafeteria.adapter.ProveedorAdapter
import com.jsandi.cafeteria.databinding.FragmentProveedorBinding
import com.jsandi.cafeteria.viewModel.ProveedorViewModel

class ProveedorFragment : Fragment() {

    private var _binding: FragmentProveedorBinding? = null

    private val binding get() = _binding!!
    private lateinit var proveedoresViewModel: ProveedorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        proveedoresViewModel = ViewModelProvider(this).get(ProveedorViewModel::class.java)
        _binding = FragmentProveedorBinding.inflate(inflater, container, false)

        binding.btAddProveedor.setOnClickListener{
            findNavController().navigate(action_nav_proveedor_to_addProveedorFragment)
        }

        val proveedorAdapter = ProveedorAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = proveedorAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        proveedoresViewModel.obtenerProveedores.observe(viewLifecycleOwner){
            proveedores -> proveedorAdapter.setProveedores(proveedores)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}