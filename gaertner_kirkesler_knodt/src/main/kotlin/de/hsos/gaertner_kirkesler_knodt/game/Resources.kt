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

    public fun spend(amount: Int) {
        this.money -= amount
    }
    public fun earn(amount: Int) {
        this.money += amount
    }
}
