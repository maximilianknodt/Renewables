package de.hsos.gaertner_kirkesler_knodt.game

import de.hsos.gaertner_kirkesler_knodt.game.SolarPark.buildingCosts
import de.hsos.gaertner_kirkesler_knodt.game.SolarPark.level
import kotlin.math.roundToInt

/**
 * Die Klasse LNG enhaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object LNG : EnergyProducer() {
    /**
     * @exception level Ist das aktuelle Level des Konstrukts, da zwischen inklusive 0 und 3 liegt
     */
    override var level: Int = 0
        set(value) {
            if(value < 0){
                field = 0
            }
            if(value > 3){
                field =3
            }
        }

    /**
     * @exception buildingCosts Ist der Preis zum Bau, oder Upgrade des Konstrukts
     */
    override var cost: Int = 1200000
        set(value){
            if(value < 700000) field = 700000
            if(value > 1200000) field = 1200000
        }

    /**
     * Gibt den Energieertrag des LNG-Tankers je nach Level zurueck
     *
     * @return Energieertrag
     */
    override fun energyOutput(): Int {
        var energy = 0
        when(level){
            0 -> energy = 0
            1 -> energy = 300000
            2 -> energy = 400000
            3 -> energy = 500000
        }
        return energy
    }

    /**
     * Gibt die Kosten zum Bau, oder Upgraden des LNG-Tankers zurueck
     *
     * @return Kosten
     */
    override fun buildingCosts(): Int {
        var cost = 0
        when(level){
            0 -> cost = 0
            1 -> cost = 1200000
            2 -> cost = 700000
            3 -> cost = 800000
        }
        return cost
    }

    /**
     * Zerstoert den LNG-Tanker je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand des LNG-Tankers
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.GIANT_LIZARD -> this.level -=1
            IncidentType.TZUNAMI, IncidentType.UFO -> this.level = 0
            IncidentType.ERUPTION -> this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(this.level == 0) super.state = Constructable()
    }

    /**
     * Aendert den Produktionszustand des LNG-Tankers
     */
    override fun construct() {
        super.state.nextState()
    }

    /**
     * Reduziert das Level des LNG-Tankers je nach Staerke des Vorfalls
     * Vorfaelle der Staerke LOW haben keinen Einfluss
     *
     * @param inc Vorfall
     */
    private fun severityImpact(inc: Incident): Int{
       // TODO: return -(level * inc.severity.avgPercentage()).roundToInt()
        when(inc.severity){
            Severity.MEDIUM -> this.level -=1
            Severity.HIGH -> this.level -= 2
            else -> println("No Impact")
        }
    }
}