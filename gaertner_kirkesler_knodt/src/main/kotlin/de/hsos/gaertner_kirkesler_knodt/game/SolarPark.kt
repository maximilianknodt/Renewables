package de.hsos.gaertner_kirkesler_knodt.game

object SolarPark : EnergyProducer() {
    override var level: Int = 0
        set(value) {
            if(value < 0){
                field = 0
            }
            if(value > 3){
                field =3
            }
        }

    override var cost: Int = 100000
        set(value){
            if(value < 50000) field = 50000
            if(value > 100000) field = 100000
        }

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

    override fun cost(): Int {
        var cost = 0
        when(level){
            0 -> cost = 0
            1 -> cost = 100000
            2 -> cost = 40000
            3 -> cost = 60000
        }
        return cost
    }

    override fun destroy(incident: Incident) {
        when(incident) {
            Incident.EARTHQUAKE -> this.level -= 1
            Incident.TZUNAMI -> this.level = 0
            Incident.FIRE, Incident.GIANT_LIZARD -> this.severityImpact(incident)
            else -> println("No Impact")
        }
        if(this.level == 0) TODO()
    }

    override fun construct() {
        TODO("Not yet implemented")
    }


    private fun severityImpact(inc: Incident){
        when(inc.severity){
            Severity.LOW -> this.level -=1
            Severity.MEDIUM -> this.level -=2
            Severity.HIGH -> this.level -= 3
        }
    }
}