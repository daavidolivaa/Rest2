package es.etg.dam.rest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ObjectDao {

    @Query("SELECT * FROM objects")
    suspend fun getAll(): List<ObjectEntity>

    @Insert
    suspend fun insertAll(objects: List<ObjectEntity>)
}
