package de.hsos.gaertner_kirkesler_knodt.game

import de.hsos.gaertner_kirkesler_knodt.BaseModel
import de.hsos.gaertner_kirkesler_knodt.game.production.EnergyProducer
import de.hsos.gaertner_kirkesler_knodt.game.population.LinearPopulation
import de.hsos.gaertner_kirkesler_knodt.routing.Route
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList

/**
 * Zentrales Model des Spiels (in-game) des JavaFX-MVC Patterns. Haelt die Daten, die die UI anzeigen soll in Form von
 * Properties, sodass mithilfe der Controller Bindings an die vom FXMLLoader generierten Views geschehen koennen.
 * Bietet ausserdem die Methoden, welche den Nutzer Aktionen durch Eingabe-Events durchfuehren lassen.
 *
 * @author Gaertner
 */
class GameModel : BaseModel() {

    val energyProducer: ObservableList<EnergyProducer>
    val resources: ObjectProperty<Resources>
    val notifications: ObjectProperty<NotificationList>

    private val simulation: Simulation

    init {
        energyProducer = SimpleListProperty<EnergyProducer>(null) // TODO: initialize with real data
        resources = SimpleObjectProperty<Resources>(Resources())
        notifications = SimpleObjectProperty<NotificationList>(NotificationList())

        // TODO
        simulation = Simulation(resources.get(), notifications.get(), energyProducer.toList(), LinearPopulation())
    }

    /**
     * Deligiert das Schliessen einer Benachrichtigung an die dafuer zustaendige NotificationList.
     *
     * @param id ID der Benachrichtigung, welche geschlossen werden soll.
     */
    fun closeNotification(id: Int) {
        print("close notification $id")
        notifications.get().close(id) // der get()-Aufruf ist notwendig, da es sich um eine ObjectProperty handelt
    }

    /**
     * Beauftragt einen State-Wechsel des EnergyProducers, sodass dieser in den Zustand 'Constructed' wechseln kann.
     * Gibt die Kosten fuer die Konstruktion des EnergyProducers vom vorhandenen Geld aus.
     *
     * @param prod EnergyProducer, welcher zu 'Constructed' gewechselt werden soll.
     */
    fun placeConstructable(prod: EnergyProducer){
        print("placeConstructable $prod")
        prod.construct()
        resources.get().spend(prod.cost)
    }

    /**
     * Startet die Simulation der naechsten Runde.
     */
    fun simulateRound(){
        print("simulateRound")
        simulation.next()
    }

    /**
     * Beendet das Spiel, indem der Router zu dem Hauptmenue wechseln soll.
     */
    fun endGame(){
        print("endGame")
        router.showScene(Route.MENU)
    }
}