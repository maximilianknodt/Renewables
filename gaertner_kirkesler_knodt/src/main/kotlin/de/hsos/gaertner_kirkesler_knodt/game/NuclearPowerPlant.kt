package de.hsos.gaertner_kirkesler_knodt.game

object NuclearPowerPlant : EnergyProducer() {
    override var level: Int = 0
        set(value) {
            if(value < 0){
                field = 0
            }
            if(value > 3){
                field =3
            }
        }

    override var cost: Int = 1500000
        set(value){
            if(value < 1000000) field = 1000000
            if(value > 1500000) field = 1500000
        }

    override fun energyOutput(): Int {
        var energy = 0
        when(SolarPark.level){
            0 -> energy = 0
            1 -> energy = 500000
            2 -> energy = 575000
            3 -> energy = 750000
        }
        return energy
    }

    override fun cost(): Int {
        var cost = 0
        when(SolarPark.level){
            0 -> cost = 0
            1 -> cost = 1500000
            2, 3 -> cost = 1000000
        }
        return cost
    }

    // EARTHQUAKE, TZUNAMI, FIRE, UFO, GIANT_LIZARD, ERUPTION
    override fun destroy(incident: Incident) {
        when(incident) {
            incident.UFO -> this.level -= 2
            Incident.TZUNAMI -> this.level = 0
            Incident.EARTHQUAKE, Incident.GIANT_LIZARD, Incident.ERUPTION -> this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(this.level == 0) TODO()
    }

    override fun construct() {
        TODO("Not yet implemented")
    }

    private fun severityImpact(inc: Incident){
        when(inc.severity){
            Severity.MEDIUM -> this.level -=1
            Severity.HIGH -> this.level -= 2
            else -> println("No Impact")
        }
    }
}