package de.hsos.gaertner_kirkesler_knodt.game

import de.hsos.gaertner_kirkesler_knodt.game.population.PopulationAlg
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList

class GameModel {

    private val energyProducer: ObservableList<EnergyProducer>
    private val resources: ObjectProperty<Resources>
    private val notifications: ObjectProperty<NotificationList>

    private val simulation: Simulation

    init {
        energyProducer = SimpleListProperty<EnergyProducer>(null) // TODO: initialize with real data
        resources = SimpleObjectProperty<Resources>(Resources())
        notifications = SimpleObjectProperty<NotificationList>(NotificationList())

        // TODO
        simualtion = Simulation(resources, energyProducer, notifications)
    }

    fun closeNotification(id: Int) {
        TODO("not implemented")
    }

    fun placeConstructable(prod: EnergyProducer){
        TODO("not implemented")
    }

    fun simulateRound(){
        TODO("not implemented")
    }

    fun endGame(){
        TODO("not implemented")
    }
}