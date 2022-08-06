package de.hsos.gaertner_kirkesler_knodt.game
/**
 * Zentrale Datenklasse fuer die Resourcen Population, Energieproduzenten, Energiekonsum und Geld.
 *
 * @author
 */
data class Resources (
    var population: Int = 0,
    var energyProduction: Int = 0,
    var energyConsumption: Int = 0,
    var money: Int = 0 ) {

    fun spend(amount: Int) {
        this.money -= amount
    }
    fun earn(amount: Int) {
        this.money += amount
    }

    override fun toString(): String {
        return "Resources(population=$population, energyProduction=$energyProduction, energyConsumption=$energyConsumption, money=$money)"
    }

    fun copy(): Resources {
        return Resources(population = population, energyProduction = energyProduction, energyConsumption = energyConsumption, money = money)
    }
}
