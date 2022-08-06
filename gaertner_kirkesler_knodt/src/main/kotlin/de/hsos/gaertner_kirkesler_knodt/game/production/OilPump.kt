package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.incident.IncidentType
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable

/**
 * Die Klasse OilPump enthaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object OilPump : EnergyProducer() {

    override var name: String = "Oelpumpe"
    override val imgPath: String = "assets/oil.png"
    override val position: Pair<Double, Double> = Pair(0.4444, 0.3417)

    override val cost: Int = 500000
        get() = when(this.level) {
            1 -> field
            2 -> 100000
            else -> 250000
        }

    override val energy = arrayOf(0, 200000, 275000, 350000)

    /**
     * Zerstoert die Oelpumpe je nach Staerke und Art des Vorfalls
     * und aendert ggf. den Zustand der Oelpumpe
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.UFO -> this.level -= 1
            IncidentType.APOCALYPSE -> this.level = 0
            IncidentType.EARTHQUAKE,
                IncidentType.TZUNAMI,
                IncidentType.FIRE,
                IncidentType.GIANT_LIZARD,
                IncidentType.ERUPTION -> this.severityImpact(incident)
            else -> println("incident does not impact energy producer ${this.name}")
        }
        if(this.level == 0) super.state = Constructable()
    }
}