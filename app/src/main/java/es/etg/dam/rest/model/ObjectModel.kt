package es.etg.dam.rest.model

import android.R

data class ObjectModel(
    val id: Int,
    val name: String,
    val data: Map<String, Any>?
)
