package es.etg.dam.rest.api

import es.etg.dam.rest.model.ObjectModel
import retrofit2.http.GET

interface ApiService {
    companion object{
        const val OBJETOS = "objects"
    }
    @GET(OBJETOS)
    suspend fun getObjects(): List<ObjectModel>
}