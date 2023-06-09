package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.incident.IncidentType
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable

/**
 * Die Klasse WindFarm enthaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object WindFarm : EnergyProducer() {

    override var name: String = "Windpark"
    override val imgPath: String = "assets/wind.png"
    override val position: Pair<Double, Double> = Pair(0.72222, 0.3167)

    override val cost: Int = 110000
        get() = when(this.level) {
            1 -> field
            2 -> 60000
            else -> 50000
        }

    override val energy = arrayOf(0, 60000, 80000, 90000)

    /**
     * Zerstoert den Windpark je nach Staerke und Art des Vorfalls
     * und aendert ggf. den Zustand des Windparks
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.UFO -> this.level -= 1
            IncidentType.TZUNAMI -> this.level -= 2
            IncidentType.EARTHQUAKE,
                IncidentType.ERUPTION,
                IncidentType.GIANT_LIZARD -> this.severityImpact(incident)
            else -> println("incident does not impact energy producer ${this.name}")
        }
        if(this.level == 0) super.state = Constructable()
    }

}