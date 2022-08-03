package de.hsos.gaertner_kirkesler_knodt.game
import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.production.EnergyProducer
import de.hsos.gaertner_kirkesler_knodt.game.population.PopulationAlg
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing
import kotlin.random.Random

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

    companion object {
        private var round: Int = 1
        private lateinit var currentIncident: Incident
        private const val EARNRATE: Int = 2
    }

    init {
        println("generating random start values")
        res.population = Random.nextInt(200, 500)
        res.money = Random.nextInt(700, 2000)
        res.energyConsumption = res.population * 2
    }
    public fun next() {
        // Runde erhoehen
        round++

        // Incident bauen
        currentIncident = Incident
            .Builder()
            .randomType()
            .withSeverityForRound(round)
            .build()

        // Bevoelkerungswachstum aufrufen
        this.res.population = this.populationAlg.evolve(
            round,
            this.res.population,
            currentIncident
        )

        // Geld verdienen
        this.res.earn(this.res.population * EARNRATE)

        // Zerstoerung aufrufen
/*        currentIncident?.run {
            energyProducer.forEach {
                it.destroy(this)
            }
        }*/
        energyProducer.forEach {
            it.destroy(currentIncident)
            if (it.state is Constructing) it.finishConstructing()
        }
    }
}