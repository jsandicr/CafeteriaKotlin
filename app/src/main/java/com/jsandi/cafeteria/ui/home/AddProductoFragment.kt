package com.jsandi.cafeteria.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.jsandi.cafeteria.R
import com.jsandi.cafeteria.databinding.FragmentAddProductoBinding
import com.jsandi.cafeteria.model.Producto
import com.jsandi.cafeteria.viewModel.HomeViewModel
import com.jsandi.cafeteria.utilidades.ImagenUtiles


class AddProductoFragment : Fragment() {

    private var _binding: FragmentAddProductoBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    //private lateinit var imagenUtiles: ImagenUtiles
    //private lateinit var tomarFotoActivity: ActivityResultLauncher<Intent>

    override fun onCreateView(
         inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentAddProductoBinding.inflate(inflater,container,false)

        binding.addProducto.setOnClickListener{
            /*binding.progressBar.visibility = ProgressBar.VISIBLE
            binding.msgMensaje.text = getString(R.string.msgSubiendo)
            binding.msgMensaje.visibility = TextView.VISIBLE
            subirImagen()*/
            agregarProducto()
        }

        //Fotos
        /*tomarFotoActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
                if(result.resultCode == Activity.RESULT_OK){
                    imagenUtiles.actualizarImagen()
                }
        }

        imagenUtiles = ImagenUtiles(requireContext(), binding.btPhoto, binding.btRotaL, binding.btRotaR, binding.imagen, tomarFotoActivity)*/

        return binding.root
    }

    /*private fun subirImagen(){
        val imagenFile = imagenUtiles.imageFile
        if(imagenFile.exists() && imagenFile.isFile && imagenFile.canRead()){
            val ruta = Uri.fromFile(imagenFile)
            val rutaNube = "productos/${Firebase.auth.currentUser?.email}/imagenes/${imagenFile.name}"
            val referencia: StorageReference = Firebase.storage.reference.child(rutaNube)
            referencia.putFile(ruta)
                .addOnSuccessListener {
                    referencia.downloadUrl
                        .addOnSuccessListener {
                            val rutaImagen = it.toString()
                            agregarProducto(rutaImagen)
                        }
                }
                .addOnCanceledListener {
                    subirImagen()
                }
        }
        else{
            agregarProducto("")
        }
    }*/

    private fun agregarProducto(){
        val nombre = binding.etName.text.toString()
        val precio = binding.etPrecio.text.toString()
        val stock = binding.etStock.text.toString()

        if(nombre.isNotEmpty()){
            var producto = Producto("", nombre, precio, stock)
            homeViewModel.guardarProducto(producto)
            Toast.makeText(requireContext(), getText(R.string.msg_AddProducto_correcto), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addProductoFragment_to_nav_home)
        }else{
            Toast.makeText(requireContext(), getText(R.string.msg_AddProducto_sinNombre), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}