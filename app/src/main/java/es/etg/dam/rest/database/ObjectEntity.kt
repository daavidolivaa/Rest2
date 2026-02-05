package es.etg.dam.rest.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "objects")
data class ObjectEntity(
    @PrimaryKey val id: String,
    val name: String,
    val data: String
)
