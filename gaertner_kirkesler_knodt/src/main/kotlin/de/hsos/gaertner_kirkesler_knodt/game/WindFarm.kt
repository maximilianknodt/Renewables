package de.hsos.gaertner_kirkesler_knodt.game

import de.hsos.gaertner_kirkesler_knodt.game.SolarPark.buildingCosts
import de.hsos.gaertner_kirkesler_knodt.game.SolarPark.level

/**
 * Die Klasse WindFarm enhaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object WindFarm : EnergyProducer() {
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
    override var cost: Int = 110000
        set(value){
            if(value < 50000) field = 50000
            if(value > 110000) field = 110000
        }

    /**
     * Gibt den Energieertrag des Windparks je nach Level zurueck
     *
     * @return Energieertrag
     */
    override fun energyOutput(): Int {
        var energy = 0
        when(level){
            0 -> energy = 0
            1 -> energy = 60000
            2 -> energy = 80000
            3 -> energy = 90000
        }
        return energy
    }

    /**
     * Gibt die Kosten zum Bau, oder Upgraden des Windparks zurueck
     *
     * @return Kosten
     */
    override fun buildingCosts(): Int {
        var cost = 0
        when(level){
            0 -> cost = 0
            1 -> cost = 110000
            2 -> cost = 60000
            3 -> cost = 50000
        }
        return cost
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

    /**
     * Aendert den Produktionszustand des Windparks
     */
    override fun construct() {
        super.state.nextState()
    }

    /**
     * Reduziert das Level des Windparks je nach Staerke des Vorfalls
     *
     * @param inc Vorfall
     */
    private fun severityImpact(inc: Incident){
        when(inc.severity){
            Severity.LOW -> this.level -=1
            Severity.MEDIUM -> this.level -=2
            Severity.HIGH -> this.level -= 3
        }
    }
}