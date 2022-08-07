package de.hsos.gaertner_kirkesler_knodt.game.simulation
import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.Resources
import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.notification.Notification
import de.hsos.gaertner_kirkesler_knodt.game.notification.NotificationList
import de.hsos.gaertner_kirkesler_knodt.game.population.ExponentialPopulation
import de.hsos.gaertner_kirkesler_knodt.game.population.LinearPopulation
import de.hsos.gaertner_kirkesler_knodt.game.population.PopulationAlg
import de.hsos.gaertner_kirkesler_knodt.game.population.StagnatingPopulation
import de.hsos.gaertner_kirkesler_knodt.game.production.*
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
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
    private var populationAlg: PopulationAlg
    private val earnRate: Double = 4.0
    private var round: Int = 1

    init {
        // Zufaellige Wahl eines Populationsalgorithmus
        val list = listOf(ExponentialPopulation(), LinearPopulation(), StagnatingPopulation())
        populationAlg = list.random()
        println("population algorithm is ${populationAlg.javaClass.simpleName}")
    }
    /**
     * Diese Methode wird nach erfolgreicher Registrierung eines Models aufgerufen um Initialwerte
     * des Spiels zu setzen.
     *
     * @author Kirkesler
     */
    private fun initValues() {
        println("created simulation, generating random start values")
        var population: Int = Random.nextInt(200, 500)
        model.setResources(Resources(
            population,
            floor(population * earnRate).toInt() + 1000,
            floor(population * earnRate).toInt(),
            Random.nextInt(50000, 60000)
        ))
    }
    /**
     * Diese Methode wird bei jeder neuen Runde aufgerufen. Berechnet neue Ressourcenwerte.
     *
     * @author Kirkesler
     */
    override fun simulate() {
        if(model != null) {
            val oldRes: Resources = model.resources.value
            val oldNot: NotificationList = model.notifications.value
            val res: Resources = oldRes.copy()
            val not: NotificationList = oldNot.copy()

            // Runde erhoehen
            round++

            // Incident bauen
            val currentIncident: Incident = Incident
                .Builder()
                .randomType()
                .withSeverityForRound(round)
                .build()
            not.add("Achtung!", "Das Ereignis ${currentIncident.type} ist mit einer Schwere von ${currentIncident.severity} eingetreten!")

            // Bevoelkerungswachstum aufrufen
            res.population = populationAlg.evolve(
                round,
                res.population,
                currentIncident
            )

            // Geld verdienen
            res.earn(kotlin.math.floor((res.population * Random.nextDouble(1.0, earnRate))).toInt())
            res.energyConsumption = kotlin.math.floor(res.population * earnRate).toInt()

            // Zerstoerung aufrufen und ggf. State erhoehen
            model.energyProducer.forEach {
                when(it.state) {
                    is Constructed -> {
                        res.energyProduction -= it.activeEnergyOutput()
                        it.destroy(currentIncident)
                        res.energyProduction += it.activeEnergyOutput()

                        if (it.state is Constructable) {
                            not.add("Zerstoert!", "Der Energieproduzent ${it.name} wurde zerstoert.")
                            model.energyProducer.remove(it)
                        }
                    }
                    is Constructing -> {
                        it.finishConstructing()
                        res.energyProduction += it.activeEnergyOutput()
                    }
                }
            }

            // Ressourcen neu setzen
            model.setResources(res)
            model.setNotifications(not)

            // Ueberpruefung, ob Spiel verloren ist
            if(res.energyProduction < res.energyConsumption && round > 3) {
                model.gameOver()
            }
        }
    }

    /**
     * Diese Methode dient zur Registrierung eines Models an einem Simulator.
     *
     * @param model GameModel, welches registriert werden soll
     *
     * @author Kirkesler
     */
    override fun register(model: GameModel) {
        this.model = model
        initValues()
    }
}