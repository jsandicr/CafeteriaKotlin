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
import com.jsandi.cafeteria.databinding.FragmentAddProveedorBinding
import com.jsandi.cafeteria.model.Proveedor
import com.jsandi.cafeteria.viewModel.ProveedorViewModel

class AddProveedorFragment : Fragment() {

    private var _binding: FragmentAddProveedorBinding? = null
    private val binding get() = _binding!!
    private lateinit var proveedorViewModel: ProveedorViewModel

    override fun onCreateView(
         inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        proveedorViewModel = ViewModelProvider(this).get(ProveedorViewModel::class.java)
        _binding = FragmentAddProveedorBinding.inflate(inflater,container,false)

        binding.addProveedor.setOnClickListener{
            agregarProveedor()
        }

        return binding.root
    }

    private fun agregarProveedor(){
        val nombre = binding.etNombre.text.toString()
        val ubicacion = binding.etUbicacion.text.toString()
        val proximaEntrega = binding.etProximaEntrega.text.toString()

        if(nombre.isNotEmpty()){
            var proveedor = Proveedor("", nombre, ubicacion, proximaEntrega)
            proveedorViewModel.guardarProveedor(proveedor)
            Toast.makeText(requireContext(), getText(R.string.msg_AddProveedor_correcto), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addProveedorFragment_to_nav_proveedor)
        }else{
            Toast.makeText(requireContext(), getText(R.string.msg_AddProveedor_sinNombre), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}