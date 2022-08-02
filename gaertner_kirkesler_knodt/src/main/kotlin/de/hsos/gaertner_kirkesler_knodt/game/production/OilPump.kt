package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.*
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing

/**
 * Die Klasse OilPump enthaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object OilPump : EnergyProducer() {
    override var level: Int = 0
        set(value) {
            if(value < 0) field = 0
            if(value > super.maxLevel) field = super.maxLevel
        }

    override var cost: Int = 500000
        get() = when(level) {
            1 -> field
            2 -> 100000
            else -> 250000
        }

    /**
     * Gibt den Energieertrag des Oelpumpe je nach Level zurueck
     *
     * @return Energieertrag
     */
    override fun energyOutput(): Int {
        val output = arrayOf(0, 200000, 275000, 350000)
        return output[level]
    }

    /**
     * Zerstoert die Oelpumpe je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand der Oelpumpe
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.UFO -> level -= 1
            IncidentType.EARTHQUAKE, IncidentType.TZUNAMI, IncidentType.FIRE, IncidentType.GIANT_LIZARD, IncidentType.ERUPTION -> this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(level == 0) super.state = Constructable()
    }

    /**
     * Aendert den Produktionszustand der Oelpumpe auf Constructing und erhoeht das Level
     */
    override fun construct() {
        level++
        super.state.nextState()
    }

    /**
     * Aendert das Level der Oelpumpe
     */
    override fun levelUp() {
        if(super.state is Constructed){
            if(level < super.maxLevel) {
                level++
            }
        }
    }

    /**
     * Aendert den Produktionszustand der Oelpumpe auf Constructed
     */
    override fun finishConstructing() {
        if(super.state is Constructing) {
            super.state.nextState()
        }
    }
}