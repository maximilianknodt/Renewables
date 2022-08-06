package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.incident.IncidentType
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable

/**
 * Die Klasse LNG enhaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object LNG : EnergyProducer() {

    override var name: String = "LNG Terminal"
    override val imgPath: String = "assets/lng.png"
    override val position: Pair<Double, Double> = Pair(0.3333, 0.4167)

    override val cost: Int = 1200000
        get() = when(this.level) {
            1 -> field
            2 -> 700000
            else -> 800000
        }

    override val energy = arrayOf(0, 300000, 400000, 500000)

    /**
     * Zerstoert den LNG-Tanker je nach Staerke und Art des Vorfalls
     * und aendert ggf. den Zustand des LNG-Tankers
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.GIANT_LIZARD -> this.level -=1
            IncidentType.TZUNAMI,
                IncidentType.UFO -> this.level = 0
            IncidentType.EARTHQUAKE,
                IncidentType.APOCALYPSE -> this.level -= this.severityImpact(incident)
            else -> println("incident does not impact energy producer ${this.name}")
        }
        if(this.level == 0) super.state = Constructable()
    }
}