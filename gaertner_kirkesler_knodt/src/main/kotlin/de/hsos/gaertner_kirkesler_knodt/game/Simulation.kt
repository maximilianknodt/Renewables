package de.hsos.gaertner_kirkesler_knodt.game
import de.hsos.gaertner_kirkesler_knodt.game.population.PopulationAlg
/**
 * Diese Klasse dient als 'Steuerklasse' fuer die Spiellogik.
 *
 * @param res Enthaelt Ressourcenwerte (Geld, Population, Energie)
 * @param notification Eine Referenz auf 'NotificationList'
 * @param energyProducer Eine Referenz auf alle gebauten 'EnergyProducer'
 * @param populationAlg Strategy-Pattern, Auswahl der Populationswachstrumsstrategie
 *
 * @author Kirkesler
 */
class Simulation(
    private val res: Resources,
    private val notification: NotificationList,
    private val energyProducer: List<EnergyProducer>,
    private val populationAlg: PopulationAlg
    ) {

    private var round: Int = 0;

    public fun next() {
        //TODO: no implementation yet
    }
}