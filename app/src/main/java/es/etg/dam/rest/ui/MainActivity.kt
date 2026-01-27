package es.etg.dam.rest.ui

import android.os.Bundle
import android.widget.Button
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
    }

    private fun cargarDatos() {
        lifecycleScope.launch {

            val datosBD = db.objectDao().getAll()

            if (datosBD.isEmpty()) {
                val datosApi = RetrofitInstance.api.getObjects()

                val entidades = datosApi.map {
                    ObjectEntity(it.id, it.name)
                }

                db.objectDao().insertAll(entidades)
                mostrar(entidades)

            } else {
                mostrar(datosBD)
            }
        }
    }

    private fun mostrar(lista: List<ObjectEntity>) {
        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ObjectAdapter(lista)
    }
}
