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
import com.jsandi.cafeteria.databinding.FragmentUpdateProductoBinding
import com.jsandi.cafeteria.model.Producto
import com.jsandi.cafeteria.viewModel.HomeViewModel

class UpdateProductoFragment : Fragment() {

    private val args by navArgs<UpdateProductoFragmentArgs>()

    private var _binding: FragmentUpdateProductoBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentUpdateProductoBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.productoArg.nombre)
        binding.etPrecio.setText(args.productoArg.precio)
        binding.etStock.setText(args.productoArg.stock)

        binding.btUpdateProducto.setOnClickListener{ updateProducto() }
        binding.btDeleteProducto.setOnClickListener{ deleteProducto() }

        return binding.root
    }

    private fun updateProducto(){
        val nombre = binding.etNombre.text.toString()
        val precio = binding.etPrecio.text.toString()
        val stock = binding.etStock.text.toString()
        if(nombre.isNotEmpty()){
            //val producto = Producto(args.productoArg.id, nombre, precio, stock, args.productoArg.rutaImagen)
            val producto = Producto(args.productoArg.id, nombre, precio, stock)
            homeViewModel.guardarProducto(producto)
            Toast.makeText(requireContext(),getString(R.string.msg_UpdateProducto_correcto), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateProductoFragment_to_nav_home)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_AddProducto_sinNombre), Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteProducto(){
        homeViewModel.eliminarProducto(args.productoArg.id)
        Toast.makeText(requireContext(),getString(R.string.msg_DeleteProducto_correcto), Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updateProductoFragment_to_nav_home)
    }
}