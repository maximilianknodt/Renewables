package de.hsos.gaertner_kirkesler_knodt.game

import de.hsos.gaertner_kirkesler_knodt.game.SolarPark.buildingCosts
import de.hsos.gaertner_kirkesler_knodt.game.SolarPark.level

/**
 * Die Klasse OilPump enhaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object OilPump : EnergyProducer() {
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
    override var cost: Int = 500000
        set(value){
            if(value < 100000) field = 100000
            if(value > 500000) field = 500000
        }

    /**
     * Gibt den Energieertrag des Oelpumpe je nach Level zurueck
     *
     * @return Energieertrag
     */
    override fun energyOutput(): Int {
        var energy = 0
        when(level){
            0 -> energy = 0
            1 -> energy = 200000
            2 -> energy = 275000
            3 -> energy = 350000
        }
        return energy
    }

    /**
     * Gibt die Kosten zum Bau, oder Upgraden der Oelpumpe zurueck
     *
     * @return Kosten
     */
    override fun buildingCosts(): Int {
         return when(level){
            0 -> 500000
            1 -> 100000
            else -> 250000
        }
    }

    /**
     * Zerstoert die Oelpumpe je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand der Oelpumpe
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.UFO -> this.level -= 1
            IncidentType.EARTHQUAKE, IncidentType.TZUNAMI, IncidentType.FIRE, IncidentType.GIANT_LIZARD, IncidentType.ERUPTION -> this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(this.level == 0) super.state = Constructable()
    }

    /**
     * Aendert den Produktionszustand desr Oelpumpe
     */
    override fun construct() {
        super.state.nextState()
    }

    /**
     * Reduziert das Level der Oelpumpe je nach Staerke des Vorfalls
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