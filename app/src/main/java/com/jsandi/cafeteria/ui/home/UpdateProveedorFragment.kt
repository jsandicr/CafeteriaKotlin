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
import com.jsandi.cafeteria.databinding.FragmentUpdateProveedorBinding
import com.jsandi.cafeteria.model.Proveedor
import com.jsandi.cafeteria.viewModel.ProveedorViewModel

class UpdateProveedorFragment : Fragment() {

    private val args by navArgs<UpdateProveedorFragmentArgs>()

    private var _binding: FragmentUpdateProveedorBinding? = null
    private val binding get() = _binding!!
    private lateinit var proveedoresViewModel: ProveedorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        proveedoresViewModel = ViewModelProvider(this).get(ProveedorViewModel::class.java)
        _binding = FragmentUpdateProveedorBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.proveedorArg.nombre)
        binding.etUbicacion.setText(args.proveedorArg.ubicacion)
        binding.etProximaEntrega.setText(args.proveedorArg.proximaEntrega)

        binding.btUpdateProveedor.setOnClickListener{ updateProveedor() }
        binding.btDeleteProveedor.setOnClickListener{ deleteProveedor() }

        return binding.root
    }

    private fun updateProveedor(){
        val nombre = binding.etNombre.text.toString()
        val ubicacion = binding.etUbicacion.text.toString()
        val proximaEntrega = binding.etProximaEntrega.text.toString()
        if(nombre.isNotEmpty()){
            val proveedor = Proveedor(args.proveedorArg.id, nombre, ubicacion, proximaEntrega)
            proveedoresViewModel.guardarProveedor(proveedor)
            Toast.makeText(requireContext(),getString(R.string.msg_UpdateProveedor_correcto), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateProveedorFragment_to_nav_proveedor)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_AddProveedor_sinNombre), Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteProveedor(){
        proveedoresViewModel.eliminarProveedor(args.proveedorArg.id)
        Toast.makeText(requireContext(),getString(R.string.msg_DeleteProveedor_correcto), Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updateProveedorFragment_to_nav_proveedor)
    }
}