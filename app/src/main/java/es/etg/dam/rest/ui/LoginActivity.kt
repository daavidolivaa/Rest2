package es.etg.dam.rest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import es.etg.dam.rest.ui.MainActivity
import es.etg.dam.rest.R
import es.etg.dam.rest.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    companion object {
        const val NOMBRE = "nombre"
        const val PREFS = "login_prefs"
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombreGuardado = leer()

        if (!nombreGuardado.isNullOrEmpty()) {
            binding.etUsuario.setText(nombreGuardado)
        }

        binding.btnLogin.setOnClickListener {
            guardar(binding.etUsuario.text.toString())
            navegar()
        }
    }

    private fun guardar(nombre: String) {
        val sharedPref = getSharedPreferences(PREFS, MODE_PRIVATE)
        sharedPref.edit().putString(NOMBRE, nombre).apply()
    }

    private fun leer(): String? {
        val sharedPref = getSharedPreferences(PREFS, MODE_PRIVATE)
        return sharedPref.getString(NOMBRE, "")
    }

    private fun navegar() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}