package com.example.appsolarsystem.data.planets.planetInfo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.PlanetInfo


@Entity(tableName = "planetInfo")
data class PlanetInfoDatabase(
    @PrimaryKey
    val id : Int,
    val planetID: Int,
    val intro: String,
    val textplanet: String,
    val formation: String,
    val distance: String,
    val orbit: String,
)


fun PlanetInfoDatabase.asDomainPlanetInfo(): PlanetInfo {
    return PlanetInfo(
        id = this.id,
        PlanetID = this.planetID,
        intro = this.intro,
        textplanet = this.textplanet,
        formation =   this.formation,
        distance = this.distance,
        orbit = this.orbit
    )
}


fun PlanetInfo.asPlanetInfoDatabase(): PlanetInfoDatabase {
    return PlanetInfoDatabase(
        id = this.id,
        planetID = this.PlanetID,
        intro = this.intro,
        textplanet = this.textplanet,
        formation =   this.formation,
        distance = this.distance,
        orbit = this.orbit

    )
}


fun List<PlanetInfoDatabase>.asDomainPlanetInfos(): List<PlanetInfo> {
    var planetInfoList = this.map {
        PlanetInfo( it.id, it.planetID, it.intro, it.textplanet, it.formation, it.distance, it.orbit)
    }
    return planetInfoList
}
