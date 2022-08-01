package de.hsos.gaertner_kirkesler_knodt.game

object OilPump : EnergyProducer() {
    override var level: Int = 0
        set(value) {
            if(value < 0){
                field = 0
            }
            if(value > 3){
                field =3
            }
        }

    override var cost: Int = 500000
        set(value){
            if(value < 100000) field = 100000
            if(value > 500000) field = 500000
        }

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

    override fun cost(): Int {
        var cost = 0
        when(level){
            0 -> cost = 0
            1 -> cost = 500000
            2 -> cost = 100000
            3 -> cost = 250000
        }
        return cost
    }

    // EARTHQUAKE, TZUNAMI, FIRE, UFO, GIANT_LIZARD, ERUPTION
    override fun destroy(incident: Incident) {
        when(incident) {
            Incident.UFO -> this.level -= 1
            Incident.EARTHQUAKE, Incident.TZUNAMI, Incident.FIRE, Incident.GIANT_LIZARD, Incident.ERUPTION -> this.severityImpact(incident)
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