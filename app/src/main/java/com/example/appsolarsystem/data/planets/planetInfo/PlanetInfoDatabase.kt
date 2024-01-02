package com.example.appsolarsystem.data.planets.planetInfo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.PlanetInfo


/**
 * Database entity representing planet information stored locally.
 *
 * @property id The unique identifier of the planet information.
 * @property planetID The identifier of the associated planet.
 * @property intro The introductory information about the planet.
 * @property textplanet Detailed textual information about the planet.
 * @property formation Information about the formation of the planet.
 * @property distance The distance of the planet from its primary celestial body.
 * @property orbit Details about the planet's orbit.
 */
@Entity(tableName = "planetInfo")
data class PlanetInfoDatabase(
    @PrimaryKey
    val id: Int,
    val planetID: Int,
    val intro: String,
    val textplanet: String,
    val formation: String,
    val distance: String,
    val orbit: String,
)

/**
 * Convert [PlanetInfoDatabase] to [PlanetInfo] domain model.
 *
 * @return The corresponding [PlanetInfo] object.
 */
fun PlanetInfoDatabase.asDomainPlanetInfo(): PlanetInfo {
    return PlanetInfo(
        id = this.id,
        PlanetID = this.planetID,
        intro = this.intro,
        textplanet = this.textplanet,
        formation = this.formation,
        distance = this.distance,
        orbit = this.orbit
    )
}

/**
 * Convert [PlanetInfo] domain model to [PlanetInfoDatabase].
 *
 * @return The corresponding [PlanetInfoDatabase] object.
 */
fun PlanetInfo.asPlanetInfoDatabase(): PlanetInfoDatabase {
    return PlanetInfoDatabase(
        id = this.id,
        planetID = this.PlanetID,
        intro = this.intro,
        textplanet = this.textplanet,
        formation = this.formation,
        distance = this.distance,
        orbit = this.orbit
    )
}

/**
 * Convert a list of [PlanetInfoDatabase] to a list of [PlanetInfo] domain models.
 *
 * @return The list of corresponding [PlanetInfo] objects.
 */
fun List<PlanetInfoDatabase>.asDomainPlanetInfos(): List<PlanetInfo> {
    return this.map {
        PlanetInfo(it.id, it.planetID, it.intro, it.textplanet, it.formation, it.distance, it.orbit)
    }
}
