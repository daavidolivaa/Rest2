package es.etg.dam.rest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ObjectDao {

    @Query("SELECT * FROM objects")
    suspend fun getAllObjects(): List<ObjectEntity>

    @Insert
    suspend fun insertAll(objects: List<ObjectEntity>)

    @Query("DELETE FROM objects")
    suspend fun deleteAll()

}
