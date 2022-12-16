package com.jsandi.cafeteria.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jsandi.cafeteria.adapter.ProductoAdapter
import com.jsandi.cafeteria.R.id.action_nav_home_to_addProductoFragment
import com.jsandi.cafeteria.databinding.FragmentHomeBinding
import com.jsandi.cafeteria.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btAddProducto.setOnClickListener{
            findNavController().navigate(action_nav_home_to_addProductoFragment)
        }

        val productoAdapter = ProductoAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = productoAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.obtenerProductos.observe(viewLifecycleOwner){
            productos -> productoAdapter.setProductos(productos)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}