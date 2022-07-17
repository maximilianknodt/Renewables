package de.hsos.gaertner_kirkesler_knodt.game

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty

class GameModel {
    val notification: ObjectProperty<Resources> = SimpleObjectProperty<Resources>(null)
}