package es.etg.dam.rest

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import es.etg.dam.rest.data.RetrofitInstance
import es.etg.dam.rest.local.AppDatabase
import es.etg.dam.rest.local.PostEntity
import es.etg.dam.rest.local.UserEntity
import es.etg.dam.rest.ui.PostAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

        findViewById<Button>(R.id.btnActualizar).setOnClickListener {
            cargarDatos()
        }
    }

    private fun cargarDatos() {
        lifecycleScope.launch {

            val postsDb = db.postDao().getAllPosts()

            if (postsDb.isEmpty()) {

                val usersApi = RetrofitInstance.api.getUsers()
                val postsApi = RetrofitInstance.api.getPosts()

                db.userDao().insertUsers(
                    usersApi.map { UserEntity(it.id, it.name) }
                )

                val postEntities = postsApi.map {
                    PostEntity(it.id, it.userId, it.title, it.body)
                }

                db.postDao().insertPosts(postEntities)
                mostrar(postEntities)

            } else {
                mostrar(postsDb)
            }
        }
    }

    private fun mostrar(posts: List<PostEntity>) {
        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = PostAdapter(posts)
    }
}