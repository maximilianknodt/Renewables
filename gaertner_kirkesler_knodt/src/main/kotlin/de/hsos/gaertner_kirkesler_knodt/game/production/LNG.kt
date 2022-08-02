package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.*
import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.incident.IncidentType
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing

/**
 * Die Klasse LNG enhaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object LNG : EnergyProducer() {
    override var level: Int = 0
        set(value) {
            if(value < 0) field = 0
            if(value > super.maxLevel) field = super.maxLevel
        }

    /**
     * @exception buildingCosts Ist der Preis zum Bau, oder Upgrade des Konstrukts
     */
    override var cost: Int = 1200000
        get() = when(level) {
            1 -> field
            2 -> 700000
            else -> 800000
        }

    /**
     * Gibt den Energieertrag des LNG-Tankers je nach Level zurueck
     *
     * @return Energieertrag
     */
    override fun energyOutput(): Int {
        val output = arrayOf(0, 300000, 400000, 500000)
        return output[level]
    }

    /**
     * Zerstoert den LNG-Tanker je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand des LNG-Tankers
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.GIANT_LIZARD -> level -=1
            IncidentType.TZUNAMI, IncidentType.UFO -> level = 0
            IncidentType.ERUPTION -> level -= this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(level == 0) super.state = Constructable()
    }

    /**
     * Aendert den Produktionszustand des LNG-Tankes auf Constructing und erhoeht das Level
     */
    override fun construct() {
        level++
        super.state.nextState()
    }

    /**
     * Aendert das Level des LNG-Tankes
     */
    override fun levelUp() {
        if(super.state is Constructed){
            if(level < super.maxLevel) {
                level++
            }
        }
    }

    /**
     * Aendert den Produktionszustand des LNG-Tankes auf Constructed
     */
    override fun finishConstructing() {
        if(super.state is Constructing) {
            super.state.nextState()
        }
    }
}