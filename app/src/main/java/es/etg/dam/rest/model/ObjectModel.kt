package es.etg.dam.rest.model

data class ObjectModel(
    val id: String,
    val name: String,
    val data: Map<String, Any>?
)
