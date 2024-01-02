package com.example.appsolarsystem.data.moons

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.Planet

/**
 * Database entity representing a Moon.
 *
 * @param moonID Unique identifier for the moon.
 * @param planetID Identifier of the associated planet.
 * @param name Name of the moon.
 * @param imageUrl URL of the moon's image.
 * @param description Description of the moon.
 * @param diameter Diameter of the moon.
 * @param mass Mass of the moon.
 */
@Entity(tableName = "moons")
data class MoonDatabase(
    @PrimaryKey
    val moonID: Int,
    val planetID: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val diameter: Double,
    val mass: Double,
)

/**
 * Extension function to convert a [MoonDatabase] object to a [Moon] domain model.
 *
 * @return A [Moon] object representing the same data.
 */
fun MoonDatabase.asDomainMoon(): Moon {
    return Moon(
        moonID = this.moonID,
        planetID = this.planetID,
        name = this.name,
        imageUrl = this.imageUrl,
        description = this.description,
        diameter = this.diameter,
        mass = this.mass,
    )
}

/**
 * Extension function to convert a [Moon] domain model to a [MoonDatabase] object.
 *
 * @return A [MoonDatabase] object representing the same data.
 */
fun Moon.asMoonDatabase(): MoonDatabase {
    return MoonDatabase(
        moonID = this.moonID,
        planetID = this.planetID,
        name = this.name,
        imageUrl = this.imageUrl,
        description = this.description,
        diameter = this.diameter,
        mass = this.mass,
    )
}

/**
 * Extension function to convert a list of [MoonDatabase] objects to a list of [Moon] domain models.
 *
 * @return A list of [Moon] objects representing the same data.
 */
fun List<MoonDatabase>.asDomainMoons(): List<Moon> {
    return this.map {
        Moon(it.moonID, it.planetID, it.name, it.imageUrl, it.description, it.diameter, it.mass)
    }
}

