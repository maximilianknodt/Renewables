package de.hsos.gaertner_kirkesler_knodt.game.simulation
import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.Resources
import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.population.PopulationAlg
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing
import java.lang.Math.floor
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
    private val populationAlg: PopulationAlg
) : Simulator {
    // TODO: Model has to be initalized, check!
    private lateinit var model: GameModel
    companion object {
        private var round: Int = 1
        private lateinit var currentIncident: Incident
        private const val EARNRATE: Double = 2.0
    }

    private fun initValues() {
        println("generating random start values")

        val res: Resources = Resources()
        res.population = Random.nextInt(200, 500)
        res.money = Random.nextInt(700, 2000)
        res.energyConsumption = res.population * 2

        model.setResources(res)
    }
    override fun simulate() {
        val oldRes: Resources = model.resources.value
        var newRes: Resources = Resources()

        // Runde erhoehen
        round++

        // Incident bauen
        currentIncident = Incident
            .Builder()
            .randomType()
            .withSeverityForRound(round)
            .build()

        // Bevoelkerungswachstum aufrufen
        newRes.population = populationAlg.evolve(
            round,
            oldRes.population,
            currentIncident
        )

        // Geld verdienen
        newRes.money = floor(oldRes.money + (oldRes.population * Random.nextDouble(1.0, EARNRATE))).toInt()
        model.setResources(newRes)

        // Zerstoerung aufrufen
/*        currentIncident?.run {
            energyProducer.forEach {
                it.destroy(this)
            }
        }*/
        model.energyProducer.forEach {
            it.destroy(currentIncident)
            if (it.state is Constructing) it.finishConstructing()
        }
    }
    override fun register(model: GameModel) {
        this.model = model
        initValues()
    }
}