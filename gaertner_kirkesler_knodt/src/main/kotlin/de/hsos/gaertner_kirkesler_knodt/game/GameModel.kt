package de.hsos.gaertner_kirkesler_knodt.game

import de.hsos.gaertner_kirkesler_knodt.BaseModel
import de.hsos.gaertner_kirkesler_knodt.game.notification.NotificationList
import de.hsos.gaertner_kirkesler_knodt.game.production.*
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.simulation.Simulation
import de.hsos.gaertner_kirkesler_knodt.game.simulation.Simulator
import de.hsos.gaertner_kirkesler_knodt.routing.Route
import de.hsos.gaertner_kirkesler_knodt.routing.Router
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList

/**
 * Zentrales Model des Spiels (in-game) des JavaFX-MVC Patterns. Haelt die Daten, die die UI anzeigen soll in Form von
 * Properties, sodass mithilfe der Controller Bindings an die vom FXMLLoader generierten Views geschehen koennen.
 * Bietet ausserdem die Methoden, welche den Nutzer Aktionen durch Eingabe-Events durchfuehren lassen.
 *
 * @author Gaertner
 */
class GameModel(
    override var router: Router
    ) : BaseModel(router) {

    val energyProducer: ObservableList<EnergyProducer>
    val resources: ObjectProperty<Resources>
    val notifications: ObjectProperty<NotificationList>

    private val simulator: Simulator = Simulation()

    init {
        val list = listOf(LNG, NuclearPowerPlant, OilPump, SolarPark, WindFarm)
        for (energyProducer in list) {
            energyProducer.resetObject()
        }
        val observableEnergyProducerList = FXCollections.observableArrayList(list)
        energyProducer = SimpleListProperty(observableEnergyProducerList)

        resources = SimpleObjectProperty(Resources())
        notifications = SimpleObjectProperty(NotificationList())
    }

    /**
     * Deligiert das Schliessen einer Benachrichtigung an die dafuer zustaendige NotificationList.
     *
     * Der verwendete get-Aufruf mit nachfolgender Bearbeitung des Objektes ausserhalb der haltenden Klasse entspricht
     * nicht der grundlegenden Idee der Objektorientierung, ist aber notwendig, da es sich um eine ObjectProperty aus
     * dem Paket javafx.beans.property handelt. Die Klasse kann alternativ nachtraeglich ergaenzt werden, indem eine
     * Extension-Methode verwendet wird. Auf diese Weise waere ein Aufruf `this.notifications.close(id)` moeglich.
     * ```
     * fun ObjectProperty<NotificationList>.close(id: Int) {
     *   this.value.close(id)
     * }
     * ```
     *
     * @param id ID der Benachrichtigung, welche geschlossen werden soll.
     */
    fun closeNotification(id: Int) {
        print("close notification $id")
        val oldNotifications: NotificationList = notifications.get() // der get()-Aufruf ist notwendig, da es sich um eine ObjectProperty handelt
        val notifications: NotificationList = oldNotifications.copy()

        notifications.close(id)
        setNotifications(notifications)
    }

    /**
     * Beauftragt einen State-Wechsel des EnergyProducers, sodass dieser in den Zustand 'Constructed' wechseln kann.
     * Gibt die Kosten fuer die Konstruktion des EnergyProducers vom vorhandenen Geld aus. Falls ein reines Levelup
     * geschehen soll, wird kein State-Wechsel durchgefuehrt.
     *
     * @param prod EnergyProducer, der gebaut oder aufgelevelt werden soll.
     */
    fun constructOrLevelupConstructable(prod: EnergyProducer){
        val index = this.energyProducer.indexOf(prod)
        val cost = prod.cost
        val res = resources.get().copy()

        if(prod.state is Constructed){
            prod.levelUp()
            println("Leveled up $prod")
        } else {
            prod.construct()
            println("constructed $prod")
        }

        res.spend(cost)
        setResources(res)
        energyProducer[index] = prod
    }

    /**
     * Startet die Simulation der naechsten Runde.
     */
    fun simulateRound(){
        simulator.simulate()
    }

    /**
     * Beendet das Spiel, indem der Router zu dem Hauptmenue wechseln soll.
     */
    fun endGame(){
        router.showScene(Route.MENU)
    }

    fun gameOver() {
        router.showScene(Route.GAMEOVER)
    }

    fun setResources(res: Resources?) {
        resources.set(res)
    }

    fun setNotifications(notifcations: NotificationList) {
        notifications.set(notifcations)
    }

    fun register() {
        simulator.register(this)
    }

    fun canPay(price: Int): Boolean {
        return resources.get().money >= price
    }
}