package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.Image
import java.net.URL
import java.util.*

/**
 * TODO: documentation
 *
 * @author Gaertner
 */
class ConstructedController : GameUIControllerBase() {

    @FXML
    private lateinit var level: Label

    @FXML
    private  lateinit var image: Image

    override fun initData(model: GameModel) {
        TODO("Not yet implemented")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        TODO("bindings")
    }
}