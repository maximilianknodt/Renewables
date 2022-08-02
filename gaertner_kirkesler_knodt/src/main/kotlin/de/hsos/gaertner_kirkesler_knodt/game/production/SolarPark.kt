package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.*
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing

/**
 * Die Klasse SolarPark enthaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object SolarPark : EnergyProducer() {
    override var level: Int = 0
        set(value) {
            if(value < 0) field = 0
            if(value > super.maxLevel) field = super.maxLevel
        }

    override var cost: Int = 100000
        get() = when(level){
            1 -> field
            2 -> 40000
            else -> 60000
        }

    /**
     * Gibt den Energieertrag des Solarparks je nach Level zurueck
     *
     * @return Energieertrag
     */
    override fun energyOutput(): Int {
        val output = arrayOf(0, 50000, 60000, 75000)
        return output[level]
    }

    /**
     * Zerstoert den Solarpark je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand des Solarparks
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.EARTHQUAKE -> level -= 1
            IncidentType.TZUNAMI -> level = 0
            IncidentType.FIRE, IncidentType.GIANT_LIZARD -> super.severityImpact(incident)
            else -> println("No Impact")
        }
        if(level == 0) super.state = Constructable()
    }

    /**
     * Aendert den Produktionszustand des Solarparks auf Constructing und erhoeht das Level
     */
    override fun construct() {
        level++
        super.state.nextState()
    }

    /**
     * Aendert das Level des Solarparks
     */
    override fun levelUp() {
        if(super.state is Constructed){
            if(level < super.maxLevel) {
                level++
            }
        }
    }

    /**
     * Aendert den Produktionszustand des Solarparks auf Constructed
     */
    override fun finishConstructing() {
        if(super.state is Constructing) {
            super.state.nextState()
        }
    }
}