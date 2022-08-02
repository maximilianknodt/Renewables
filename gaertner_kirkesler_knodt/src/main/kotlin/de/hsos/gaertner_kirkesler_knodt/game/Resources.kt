package de.hsos.gaertner_kirkesler_knodt.game

data class Resources (
    var population: Int = 0,
    var energyProduction: Int = 0,
    var energyConsumption: Int = 0,
    var energyDemand: Int = 0,
    var money: Int = 0 ) {

    public fun spend(amount: Int) {
        this.money -= amount
    }
    public fun earn(amount: Int) {
        this.money += amount
    }
}
