package com.jsandi.cafeteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jsandi.cafeteria.databinding.ActivityAuthBinding
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser

class AuthActivity : AppCompatActivity() {

    //Objeto Firebase
    private lateinit var auth: FirebaseAuth

    //Pantalla XML
    private lateinit var binding: ActivityAuthBinding
    private val Google_Sign_In = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializar Auth
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.signUp.setOnClickListener{ registrar() }
        binding.signIn.setOnClickListener{ login() }
        binding.btGoogle.setOnClickListener{ loginGoogle() }
    }

    private fun registrar(){
        val email = binding.emailEditText.text.toString()
        val contraseña = binding.passwordEditText.text.toString()

        auth.createUserWithEmailAndPassword(email, contraseña)
            .addOnCompleteListener(this){
                    task -> if(task.isSuccessful){
                    val user = auth.currentUser
                    cargarPantalla(user)
                }else{
                    Toast.makeText(baseContext, "Falta ingresar datos", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun cargarPantalla(user: FirebaseUser?){
        if(user != null){
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(){
        val email = binding.emailEditText.text.toString()
        val contraseña = binding.passwordEditText.text.toString()

        auth.signInWithEmailAndPassword(email, contraseña)
            .addOnCompleteListener{ result ->
                Log.d("TAG", result.toString())
                if(result.isSuccessful){
                    val user = auth.currentUser
                    cargarPantalla(user)
                }else{
                    Toast.makeText(baseContext, R.string.no_login, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun loginGoogle() {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            //remplazar por getString(R.String.default_web_client_id)
            .requestIdToken("1060466838643-ph9oic8n4nco24uthi86jri6quq4nluh.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val googleClient = GoogleSignIn.getClient(this, googleConf)
        googleClient.signOut();

        startActivityForResult(googleClient.signInIntent, Google_Sign_In)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Google_Sign_In){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if(account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                        if(it.isSuccessful){
                            showHome(account.email ?: "")
                        }
                        else{
                            showAlert()
                        }
                    }
                }
            }catch(e: ApiException) {
                showAlert()
            }
        }
    }

    private fun showHome(email: String){
        val intent: Intent = Intent(this, PrincipalActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(intent)
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error authenticando")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        cargarPantalla(user)
    }

}