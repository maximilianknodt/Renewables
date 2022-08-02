package de.hsos.gaertner_kirkesler_knodt.game

/**
 * Die Klasse SolarPark enhaelt Kosten, Energieertrag und verschiedene Reaktion auf Katastrophen
 *
 * @author Knodt
 */
object SolarPark : EnergyProducer() {
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
    override var cost: Int = 100000
        set(value){
            if(value < 40000) field = 40000
            if(value > 100000) field = 100000
        }

    /**
     * Gibt den Energieertrag des Solarparks je nach Level zurueck
     *
     * @return Energieertrag
     */
    override fun energyOutput(): Int {
        var energy = 0
        when(level){
            0 -> energy = 0
            1 -> energy = 50000
            2 -> energy = 60000
            3 -> energy = 75000
        }
        return energy
    }

    /**
     * Gibt die Kosten zum Bau, oder Upgraden des Solarparks zurueck
     *
     * @return Kosten
     */
    override fun buildingCosts(): Int {
        var cost = 0
        when(level){
            0 -> cost = 0
            1 -> cost = 100000
            2 -> cost = 40000
            3 -> cost = 60000
        }
        return cost
    }

    /**
     * Zerstoert den Solarpark je nach Staerke und Art des Vorfall
     * und aendert ggf. den Zustand des Solarparks
     *
     * @param incident Vorfall
     */
    override fun destroy(incident: Incident) {
        when(incident.type) {
            IncidentType.EARTHQUAKE -> this.level -= 1
            IncidentType.TZUNAMI -> this.level = 0
            IncidentType.FIRE, IncidentType.GIANT_LIZARD -> this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(this.level == 0) super.state = Constructable()
    }

    /**
     * Aendert den Produktionszustand des Solarparks
     */
    override fun construct() {
        super.state.nextState()
    }

    /**
     * Reduziert das Level des Solarparks je nach Staerke des Vorfalls
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