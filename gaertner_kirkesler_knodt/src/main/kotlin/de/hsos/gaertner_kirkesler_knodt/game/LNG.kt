package de.hsos.gaertner_kirkesler_knodt.game

object LNG : EnergyProducer() {
    override var level: Int = 0
        set(value) {
            if(value < 0){
                field = 0
            }
            if(value > 3){
                field =3
            }
        }

    override var cost: Int = 1200000
        set(value){
            if(value < 700000) field = 700000
            if(value > 1200000) field = 1200000
        }

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

    override fun cost(): Int {
        var cost = 0
        when(level){
            0 -> cost = 0
            1 -> cost = 1200000
            2 -> cost = 700000
            3 -> cost = 800000
        }
        return cost
    }

    // EARTHQUAKE, TZUNAMI, FIRE, UFO, GIANT_LIZARD, ERUPTION
    override fun destroy(incident: Incident) {
        when(incident) {
            Incident.GIANT_LIZARD -> this.level -=1
            Incident.TZUNAMI, Incident.UFO -> this.level = 0
            Incident.ERUPTION -> this.severityImpact(incident)
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