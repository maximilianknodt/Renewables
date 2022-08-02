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
class ResourcesController : GameUIControllerBase() {

    @FXML
    private lateinit var energy: Label

    @FXML
    private lateinit var population: Label

    @FXML
    private lateinit var money: Label

    override fun initData(model: GameModel) {
        //TODO("Not yet implemented")
        println("ResourcesController.initData()")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        //TODO("bindings")
        println("ResourcesController.initialize()")
    }
}