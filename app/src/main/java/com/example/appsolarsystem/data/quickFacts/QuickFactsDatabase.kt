package com.example.appsolarsystem.data.quickFacts

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appsolarsystem.model.QuickFact

@Entity(tableName = "quickFacts")
data class QuickFactDatabase(
    @PrimaryKey
    val id : Int,
    val planetID : Int,
    val recordedBy : String?,
    val firstRecorded : String?,
    val surfaceTempurature : String?,
    val orbitPeriod : String?,
    val orbitDistance : String?,
    val knownRings : Int?,
    val notableMoons : String?,
    val knownMoons : Int?,
    val equatorialCircumference : String?,
    val polarDiameter : String?,
    val equatorialDiameter : String?,
    val mass : String?
)

fun QuickFactDatabase.asDomainQuickFact() : QuickFact {
    return QuickFact(
        id = this.id,
        planetID = this.planetID,
        recordedBy = this.recordedBy,
        firstRecorded = this.firstRecorded,
        surfaceTempurature = this.surfaceTempurature,
        orbitPeriod = this.orbitPeriod,
        orbitDistance = this.orbitDistance,
        knownRings = this.knownRings,
        notableMoons = this.notableMoons,
        knownMoons = this.knownMoons,
        equatorialCircumference = this.equatorialCircumference,
        polarDiameter = this.polarDiameter,
        equatorialDiameter = this.equatorialDiameter,
        mass = this.mass
    )
}

fun QuickFact.asQuickFactDatabase(): QuickFactDatabase? {
    return QuickFactDatabase(
        id = this.id,
        planetID = this.planetID,
        recordedBy = this.recordedBy,
        firstRecorded = this.firstRecorded,
        surfaceTempurature = this.surfaceTempurature,
        orbitPeriod = this.orbitPeriod,
        orbitDistance = this.orbitDistance,
        knownRings = this.knownRings,
        notableMoons = this.notableMoons,
        knownMoons = this.knownMoons,
        equatorialCircumference = this.equatorialCircumference,
        polarDiameter = this.polarDiameter,
        equatorialDiameter = this.equatorialDiameter,
        mass = this.mass
    )
}
