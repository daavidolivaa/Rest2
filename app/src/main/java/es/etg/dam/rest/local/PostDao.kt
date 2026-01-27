package es.etg.dam.rest.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<PostEntity>

    @Insert
    suspend fun insertPosts(posts: List<PostEntity>)
}