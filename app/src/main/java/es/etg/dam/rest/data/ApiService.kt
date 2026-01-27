package es.etg.dam.rest.data

import es.etg.dam.rest.model.Post
import es.etg.dam.rest.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("users")
    suspend fun getUsers(): List<User>
}
