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
    override var cost: Int = 110000
        get() = when(this.level) {
            1 -> field
            2 -> 60000
            else -> 50000
        }

    /**
     * Gibt den Energieertrag des Windparks je nach Level zurueck
     *
     * @return Energieertrag
     */
    override fun energyOutput(): Int {
        val output = arrayOf(0, 60000, 80000, 90000)
        return output[this.level]
    }

    /**
     * Zerstoert den Windpark je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand des Windparks
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.UFO -> this.level -= 1
            IncidentType.TZUNAMI -> this.level -= 2
            IncidentType.EARTHQUAKE, IncidentType.GIANT_LIZARD -> this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(this.level == 0) super.state = Constructable()
    }
}