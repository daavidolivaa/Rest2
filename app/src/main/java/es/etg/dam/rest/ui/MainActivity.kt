package es.etg.dam.rest.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import es.etg.dam.rest.R
import es.etg.dam.rest.api.RetrofitInstance
import es.etg.dam.rest.database.AppDatabase
import es.etg.dam.rest.database.ObjectEntity
import es.etg.dam.rest.databinding.ActivityMainBinding
import es.etg.dam.rest.ui.adapter.ObjectAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOMBRE_BD = "app_db"
        const val TAG = "ORIGEN"
        const val MSG_REST = "Actualizando desde REST"
        const val MSG_BD_VACIA = "BD vacía -> REST"
        const val MSG_BD_DATOS = "BD con datos -> cargar BD"
        const val MSG_BORRAR = "BD borrada"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargar los datos desde el room
        setRoom()

        // Cargar datos al iniciar app
        comprobarDatosIniciales()

        // Botón actualizar
        binding.btnActualizar.setOnClickListener {
            actualizarDesdeRest()
        }

        // Boton borrar
        binding.btnBorrarBD.setOnClickListener {
            borrarBaseDeDatos()
        }

    }
    private fun setRoom(){
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            NOMBRE_BD
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    private fun comprobarDatosIniciales() {
        lifecycleScope.launch {

            val datosBD = db.objectDao().getAllObjects()

            if (datosBD.isEmpty()) {
                Log.d(TAG, MSG_BD_VACIA)

                val datosApi = RetrofitInstance.api.getObjects()

                val entidades = datosApi.map {
                    ObjectEntity(it.id, it.name, it.data.toString())
                }

                db.objectDao().insertAll(entidades)

                mostrarEnListView(entidades)

            } else {
                Log.d(TAG, MSG_BD_DATOS)
                mostrarEnListView(datosBD)
            }
        }
    }

    private fun actualizarDesdeRest() {
        lifecycleScope.launch {

            Log.d(TAG, MSG_REST)

            db.objectDao().deleteAll()

            val datosApi = RetrofitInstance.api.getObjects()

            val entidades = datosApi.map {
                ObjectEntity(it.id, it.name, it.data.toString())
            }

            db.objectDao().insertAll(entidades)

            mostrarEnListView(entidades)
        }
    }

    private fun borrarBaseDeDatos() {
        lifecycleScope.launch {

            db.objectDao().deleteAll()

            Log.d(TAG, MSG_BORRAR)

            mostrarEnListView(emptyList())
        }
    }
    private fun mostrarEnListView(lista: List<ObjectEntity>) {
        val adapter = ObjectAdapter(this, lista)
        binding.listView.adapter = adapter
    }
}