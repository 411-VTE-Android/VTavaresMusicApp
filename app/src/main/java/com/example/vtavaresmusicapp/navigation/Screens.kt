package com.example.vtavaresmusicapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@kotlinx.serialization.Serializable
data class Detail(
    val id: String
)
