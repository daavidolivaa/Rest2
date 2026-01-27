package es.etg.dam.rest.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ObjectEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun objectDao(): ObjectDao
}
