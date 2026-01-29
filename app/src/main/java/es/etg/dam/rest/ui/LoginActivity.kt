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


class LoginActivity : AppCompatActivity() {

        companion object {
            const val NOMBRE = "nombre"
            const val PREFS = "login_prefs"
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)


            val edtUsuario = findViewById<EditText>(R.id.etUsuario)
            val edtPass = findViewById<EditText>(R.id.etPassword)
            val btnLogin = findViewById<Button>(R.id.btnLogin)

            val nombreGuardado = leer()

            if (nombreGuardado != null && nombreGuardado.isNotEmpty()) {
                edtUsuario.setText(nombreGuardado)
            }

            btnLogin.setOnClickListener {
                guardar(edtUsuario.text.toString())
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