package com.example.appsolarsystem.data.planets

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appsolarsystem.model.Planet

@Entity(tableName = "planets")
data class PlanetDatabase(
    @PrimaryKey
    val planetID: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val diameter: Int,
    val mass: Double,
    val gravity: Double,
)


fun PlanetDatabase.asDomainPlanet(): Planet {
    return Planet(
        planetID = this.planetID,
        name = this.name,
        imageUrl = this.imageUrl,
        description = this.description,
        diameter = this.diameter,
        mass = this.mass,
        gravity = this.gravity
     )
}


fun Planet.asPlanetDatabase(): PlanetDatabase {
    return PlanetDatabase(
        planetID = this.planetID,
        name = this.name,
        imageUrl = this.imageUrl,
        description = this.description,
        diameter = this.diameter,
        mass = this.mass,
        gravity = this.gravity
    )
}


fun List<PlanetDatabase>.asDomainPlanets(): List<Planet> {
    var planetList = this.map {
        Planet(it.planetID, it.name, it.imageUrl, it.description, it.diameter, it.mass, it.gravity)
    }
    return planetList
}