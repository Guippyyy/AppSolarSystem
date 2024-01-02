package com.example.appsolarsystem.data.planets

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appsolarsystem.model.Planet
/**
 * Entity class representing a planet in the local database.
 *
 * @param planetID The unique identifier of the planet.
 * @param name The name of the planet.
 * @param imageUrl The URL to the image of the planet.
 * @param description A description of the planet.
 * @param diameter The diameter of the planet.
 * @param mass The mass of the planet.
 * @param gravity The gravitational force of the planet.
 */
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

/**
 * Extension function to convert a [PlanetDatabase] object into a [Planet] domain model.
 *
 * @return A [Planet] object representing the same planet data.
 */
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

/**
 * Extension function to convert a [Planet] domain model into a [PlanetDatabase] object.
 *
 * @return A [PlanetDatabase] object representing the same planet data.
 */
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

/**
 * Extension function to convert a list of [PlanetDatabase] objects into a list of [Planet] domain models.
 *
 * @return A list of [Planet] objects representing the same planet data.
 */
fun List<PlanetDatabase>.asDomainPlanets(): List<Planet> {
    return this.map {
        Planet(it.planetID, it.name, it.imageUrl, it.description, it.diameter, it.mass, it.gravity)
    }
}
