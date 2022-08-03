package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import javafx.fxml.Initializable

/**
 * Basis fuer MVC-Controller im Spiel. Bietet eine Methode [initData],
 * um dem Controller das GameModel [model] bekannt zu machen.
 *
 * @author Gaertner
 */
abstract class GameUIControllerBase : Initializable {

    protected lateinit var model: GameModel

    open fun initData(model: GameModel) {
        this.model = model
    }

}