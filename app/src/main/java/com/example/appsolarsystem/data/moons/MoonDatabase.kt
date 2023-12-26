package com.example.appsolarsystem.data.moons

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.Planet

@Entity(tableName = "moons")
data class MoonDatabase(
    @PrimaryKey
    val moonID : Int,
    val planetID: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val diameter: Int,
    val mass: Double,
)


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


fun List<MoonDatabase>.asDomainMoons(): List<Moon> {
    var moonList = this.map {
        Moon( it.moonID, it.planetID, it.name, it.imageUrl, it.description, it.diameter, it.mass)
    }
    return moonList
}
