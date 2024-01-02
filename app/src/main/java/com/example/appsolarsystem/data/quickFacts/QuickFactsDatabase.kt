package com.example.appsolarsystem.data.quickFacts

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appsolarsystem.model.QuickFact

/**
 * Entity representing a quick fact about a celestial body stored in the local database.
 *
 * @property id The unique identifier of the quick fact.
 * @property planetID The identifier of the planet to which the quick fact belongs.
 * @property recordedBy The entity or source that recorded the quick fact.
 * @property firstRecorded The date when the quick fact was first recorded.
 * @property surfaceTempurature The surface temperature of the celestial body.
 * @property orbitPeriod The orbital period of the celestial body.
 * @property orbitDistance The orbital distance of the celestial body.
 * @property knownRings The number of known rings around the celestial body.
 * @property notableMoons Descriptive information about notable moons.
 * @property knownMoons The number of known moons orbiting the celestial body.
 * @property equatorialCircumference The equatorial circumference of the celestial body.
 * @property polarDiameter The polar diameter of the celestial body.
 * @property equatorialDiameter The equatorial diameter of the celestial body.
 * @property mass The mass of the celestial body.
 */
@Entity(tableName = "quickFacts")
data class QuickFactDatabase(
    @PrimaryKey
    val id: Int,
    val planetID: Int,
    val recordedBy: String?,
    val firstRecorded: String?,
    val surfaceTempurature: String?,
    val orbitPeriod: String?,
    val orbitDistance: String?,
    val knownRings: Int?,
    val notableMoons: String?,
    val knownMoons: Int?,
    val equatorialCircumference: String?,
    val polarDiameter: String?,
    val equatorialDiameter: String?,
    val mass: String?
)

/**
 * Converts a [QuickFactDatabase] entity to a [QuickFact] domain model.
 *
 * @receiver The [QuickFactDatabase] entity to convert.
 * @return The equivalent [QuickFact] domain model.
 */
fun QuickFactDatabase.asDomainQuickFact(): QuickFact {
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

/**
 * Converts a [QuickFact] domain model to a [QuickFactDatabase] entity.
 *
 * @receiver The [QuickFact] domain model to convert.
 * @return The equivalent [QuickFactDatabase] entity, or null if the domain model is null.
 */
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
