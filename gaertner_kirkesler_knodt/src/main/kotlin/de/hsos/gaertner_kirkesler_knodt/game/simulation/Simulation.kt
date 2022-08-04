package de.hsos.gaertner_kirkesler_knodt.game.simulation
import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.Resources
import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.population.ExponentialPopulation
import de.hsos.gaertner_kirkesler_knodt.game.population.LinearPopulation
import de.hsos.gaertner_kirkesler_knodt.game.population.PopulationAlg
import de.hsos.gaertner_kirkesler_knodt.game.population.StagnatingPopulation
import de.hsos.gaertner_kirkesler_knodt.game.production.*
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing
import kotlin.math.floor
import kotlin.random.Random

/**
 * Diese Klasse dient als 'Steuerklasse' fuer die Spiellogik.
 *
 * @param populationAlg Strategy-Pattern, Auswahl der Populationswachstrumsstrategie
 *
 * @author Kirkesler
 */
class Simulation() : Simulator {
    private lateinit var model: GameModel
    private lateinit var populationAlg: PopulationAlg
    private val EARNRATE: Double = 4.0
    companion object {
        private var ROUND: Int = 1
    }

    init {
        val list = listOf(ExponentialPopulation(), LinearPopulation(), StagnatingPopulation())
        populationAlg = list.random()
        println("Algo is $populationAlg")
    }

    private fun initValues() {
        println("generating random start values")
        var population: Int = Random.nextInt(200, 500)
        model.setResources(Resources(
            population,
            Random.nextInt(400, 1000),
            floor(population * EARNRATE).toInt(),
            Random.nextInt(30000, 60000)
        ))
    }
    override fun simulate() {
        if(model != null) {
            val oldRes: Resources = model.resources.value

            // Runde erhoehen
            ROUND++

            // Incident bauen
            val currentIncident: Incident = Incident
                .Builder()
                .randomType()
                .withSeverityForRound(ROUND)
                .build()

            // Bevoelkerungswachstum aufrufen
            var population = populationAlg.evolve(
                ROUND,
                oldRes.population,
                currentIncident
            )

            // Geld verdienen
            var money = kotlin.math.floor(oldRes.money + (oldRes.population * Random.nextDouble(1.0, EARNRATE))).toInt()
            var energyConsumption = kotlin.math.floor(oldRes.population * EARNRATE).toInt()

            var energyProduction: Int = oldRes.energyProduction
            // Zerstoerung aufrufen und ggf. State erhoehen
            model.energyProducer.forEach {
                it.destroy(currentIncident)
                if (it.state is Constructing) it.finishConstructing()
                if (it.state is Constructed) energyProduction += it.energyOutput()
            }

            // TODO: EnergyConsumption und Demand fehlen
            model.setResources(Resources(
                population,
                energyProduction,
                energyConsumption,
                money
            ))
        }
    }
    override fun register(model: GameModel) {
        this.model = model
        initValues()
    }
}