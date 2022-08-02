package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.EnergyProducer
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
class ConstructableController : GameUIControllerBase() {

    lateinit var prod: EnergyProducer

    @FXML
    private lateinit var name: Label

    @FXML
    private lateinit var energy: Label

    @FXML
    private lateinit var cost: Label

    @FXML
    private lateinit var image: Image

    override fun initData(model: GameModel) {
        TODO("Not yet implemented")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // TODO: hier müssen keine Listener rein, oder?
    }

    @FXML
    fun onClick() {
        this.model.placeConstructable(prod)
    }

}
