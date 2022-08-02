package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.incident.IncidentType
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing

/**
 * Die Klasse NuclearPowerPlant enthaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object NuclearPowerPlant : EnergyProducer() {
    override var level: Int = 0
        set(value) {
            if(value < 0) field = super.maxLevel
            if(value > 3) field = super.maxLevel
        }

    override val cost: Int = 1500000
        get() = when (level) {
                1 -> field
                else -> 1000000
            }

    /**
     * Gibt den Energieertrag des Atomkraftwerks je nach Level zurueck
     *
     * @return Energieertrag
     */
    override fun energyOutput(): Int {
        val output = arrayOf(0, 500000, 575000, 750000)
        return output[level]
    }

    /**
     * Zerstoert das Atomkraftwerk je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand des Atomkraftwerks
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.UFO -> level -= 2
            IncidentType.TZUNAMI -> level = 0
            IncidentType.EARTHQUAKE,
            IncidentType.GIANT_LIZARD,
            IncidentType.ERUPTION -> level -= super.severityImpact(incident)
            else -> println("No Impact")
        }
        if(level == 0) super.state = Constructable()
    }

    /**
     * Aendert den Produktionszustand des Atomkraftwerks auf Constructing und erhoeht das Level
     */
    override fun construct() {
        level++
        super.state.nextState()
    }

    /**
     * Aendert das Level des Atomkraftwerks
     */
    override fun levelUp() {
        if(super.state is Constructed){
            if(level < super.maxLevel) {
                level++
            }
        }
    }

    /**
     * Aendert den Produktionszustand des Atomkraftwerks auf Constructed
     */
    override fun finishConstructing() {
        if(super.state is Constructing) {
            super.state.nextState()
        }
    }
}