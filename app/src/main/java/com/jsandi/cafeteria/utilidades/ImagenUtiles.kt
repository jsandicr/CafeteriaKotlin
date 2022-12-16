package com.jsandi.cafeteria.utilidades

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.FileProvider
import com.jsandi.cafeteria.BuildConfig
import java.io.File

class ImagenUtiles (
    private val context: Context,
    btPhoto: ImageButton,
    btRotaL: ImageButton,
    btRotaR: ImageButton,
    private val image: ImageView,
    private var tomarFotoActivity: ActivityResultLauncher<Intent>
    ){
    init{
        btPhoto.setOnClickListener { tomarFoto() }
        btRotaL.setOnClickListener { image.rotation = image.rotation-90f }
        btRotaR.setOnClickListener { image.rotation = image.rotation+90f }
    }
    lateinit var imageFile: File
    private lateinit var currentPhotoPath: String

    private fun tomarFoto(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(context.packageManager) != null){
            imageFile = crearImagenFile()
            val photoUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, imageFile)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            tomarFotoActivity.launch(intent)
        }
    }

    private fun crearImagenFile(): File {
        val archivo = OtrosUtiles.getTempFile("image_")
        val storeDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(archivo, ".jpg", storeDir)
        currentPhotoPath = image.absolutePath
        return image
    }

    fun actualizarImagen(){
        image.setImageBitmap(BitmapFactory.decodeFile(imageFile.absolutePath))
    }

}