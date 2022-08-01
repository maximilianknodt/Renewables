package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import javafx.fxml.Initializable

/**
 * TODO: documentation
 *
 * @author Gaertner
 */
abstract class GameUIControllerBase : Initializable {

    protected lateinit var model: GameModel

    abstract fun initData(model: GameModel)

}