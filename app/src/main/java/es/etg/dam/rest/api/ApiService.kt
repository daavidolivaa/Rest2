package es.etg.dam.rest.api

import es.etg.dam.rest.model.ObjectModel
import retrofit2.http.GET

interface ApiService {

    @GET("objects")
    suspend fun getObjects(): List<ObjectModel>
}