package es.etg.dam.rest.local

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDao {

    @Insert
    suspend fun insertUsers(users: List<UserEntity>)
}