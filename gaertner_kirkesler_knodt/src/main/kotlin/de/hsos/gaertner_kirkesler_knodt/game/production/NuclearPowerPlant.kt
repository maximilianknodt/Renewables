package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.incident.IncidentType
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable

/**
 * Die Klasse NuclearPowerPlant enthaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object NuclearPowerPlant : EnergyProducer() {

    override var name: String = "Atomkraftwerk"
    override val imgPath: String = "assets/atomic.png"
    override val position: Pair<Double, Double> = Pair(0.5889, 0.2667)

    override val cost: Int = 1500000
        get() = when (this.level) {
                1 -> field
                else -> 1000000
            }

    override val energy = arrayOf(0, 500000, 575000, 750000)

    /**
     * Zerstoert das Atomkraftwerk je nach Staerke und Art des Vorfalls
     * und aendert ggf. den Zustand des Atomkraftwerks
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.UFO -> this.level -= 2
            IncidentType.TZUNAMI -> this.level = 0
            IncidentType.EARTHQUAKE,
                IncidentType.GIANT_LIZARD,
                IncidentType.APOCALYPSE-> this.level -= this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(this.level == 0) super.state = Constructable()
    }
}