package es.etg.dam.rest.database

import android.R
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "objects")
data class ObjectEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val data: String
)
