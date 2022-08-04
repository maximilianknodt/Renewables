package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.incident.IncidentType
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable

/**
 * Die Klasse SolarPark enthaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object SolarPark : EnergyProducer() {

    override var name: String = "Solarpark"
    override val imgPath: String = "assets/solar.png"
    override val position: Pair<Double, Double> = Pair(0.2167, 0.3750)

    override var cost: Int = 100000
        get() = when(this.level){
            1 -> field
            2 -> 40000
            else -> 60000
        }

    override val energy = arrayOf(0, 50000, 60000, 75000)

    /**
     * Zerstoert den Solarpark je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand des Solarparks
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.EARTHQUAKE -> this.level -= 1
            IncidentType.TZUNAMI -> this.level = 0
            IncidentType.FIRE, IncidentType.GIANT_LIZARD -> this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(this.level == 0) super.state = Constructable()
    }
}