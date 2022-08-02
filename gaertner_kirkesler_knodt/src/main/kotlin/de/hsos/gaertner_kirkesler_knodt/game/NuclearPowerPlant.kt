package de.hsos.gaertner_kirkesler_knodt.game

import de.hsos.gaertner_kirkesler_knodt.game.SolarPark.buildingCosts
import de.hsos.gaertner_kirkesler_knodt.game.SolarPark.level

/**
 * Die Klasse NuclearPowerPlant enhaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object NuclearPowerPlant : EnergyProducer() {
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
        var energy = 0
        when(level){
            0 -> energy = 0
            1 -> energy = 500000
            2 -> energy = 575000
            3 -> energy = 750000
        }
        return energy
    }

    /**
     * Zerstoert das Atomkraftwerk je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand des Atomkraftwerks
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.UFO -> this.level -= 2
            IncidentType.TZUNAMI -> this.level = 0
            IncidentType.EARTHQUAKE, IncidentType.GIANT_LIZARD, IncidentType.ERUPTION -> this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(this.level == 0) super.state = Constructable()
    }

    /**
     * Aendert den Produktionszustand des Atomkraftwerks
     */
    override fun construct() {
        super.state.nextState()
    }

    /**
     * Reduziert das Level des Atomkraftwerks je nach Staerke des Vorfalls
     * Vorfaelle der Staerke LOW haben keinen Einfluss
     *
     * @param inc Vorfall
     */
    private fun severityImpact(inc: Incident){
        when(inc.severity){
            Severity.MEDIUM -> this.level -=1
            Severity.HIGH -> this.level -= 2
            else -> println("No Impact")
        }
    }
}