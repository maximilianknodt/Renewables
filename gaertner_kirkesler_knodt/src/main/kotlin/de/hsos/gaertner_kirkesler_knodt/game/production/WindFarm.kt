package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.*
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing

/**
 * Die Klasse WindFarm enthaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object WindFarm : EnergyProducer() {
    override var level: Int = 0
        set(value) {
            if(value < 0) field = 0
            if(value > super.maxLevel) field = super.maxLevel
        }

    override var cost: Int = 110000
        get() = when(level) {
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
        return output[level]
    }

    /**
     * Zerstoert den Windpark je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand des Windparks
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.UFO -> level -= 1
            IncidentType.TZUNAMI -> level -= 2
            IncidentType.EARTHQUAKE, IncidentType.GIANT_LIZARD -> this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(level == 0) super.state = Constructable()
    }

    /**
     * Aendert den Produktionszustand des Windparks auf Constructing und erhoeht das Level
     */
    override fun construct() {
        level++
        super.state.nextState()
    }

    /**
     * Aendert das Level des Windparks
     */
    override fun levelUp() {
        if(super.state is Constructed){
            if(level < super.maxLevel) {
                level++
            }
        }
    }

    /**
     * Aendert den Produktionszustand des Windparks auf Constructed
     */
    override fun finishConstructing() {
        if(super.state is Constructing) {
            super.state.nextState()
        }
    }
}