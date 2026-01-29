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
import es.etg.dam.rest.ui.adapter.ObjectAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_db"
        ).build()

        findViewById<Button>(R.id.btnActualizar).setOnClickListener {
            cargarDatos()
        }

        findViewById<Button>(R.id.btnBorrarBD).setOnClickListener {
            borrarBaseDeDatos()
        }
    }

    private fun cargarDatos() {
        lifecycleScope.launch {

            val datosBD = db.objectDao().getAllObjects()

            if (datosBD.isEmpty()) {

                Log.d("ORIGEN", "BD vacía REST")

                val datosApi = RetrofitInstance.api.getObjects()

                val entidades = datosApi.map {
                    ObjectEntity(
                        id = it.id,
                        name = it.name
                    )
                }
                db.objectDao().insertAll(entidades)


            }

            Log.d("ORIGEN", "Mostrando desde BD")
            val datosFinales = db.objectDao().getAllObjects()
            mostrarEnListView(datosFinales)
        }
    }
    private fun mostrarEnListView(lista: List<ObjectEntity>) {
        val listView = findViewById<ListView>(R.id.listView)
        val adapter = ObjectAdapter(this, lista)
        listView.adapter = adapter
    }
    private fun borrarBaseDeDatos() {
        lifecycleScope.launch {
            db.objectDao().deleteAll()
            Log.d("ORIGEN", "BD borrada")
            val listaVacia = db.objectDao().getAllObjects()
            mostrarEnListView(listaVacia)
        }
    }

}

