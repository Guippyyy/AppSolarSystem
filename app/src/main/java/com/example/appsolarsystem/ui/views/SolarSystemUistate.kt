package com.example.appsolarsystem.ui.views

import com.example.appsolarsystem.model.Planet

data class SolarSystemUiState(
    val success: List<Planet>? = null,
    val Error: String? = null,
    val loading: Boolean = false,
){

    companion object {
        val loading = SolarSystemUiState(loading = true)
    }

}
