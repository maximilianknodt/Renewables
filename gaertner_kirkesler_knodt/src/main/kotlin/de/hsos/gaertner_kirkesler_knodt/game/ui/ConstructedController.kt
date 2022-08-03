package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.production.EnergyProducer
import javafx.collections.ListChangeListener
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.Image
import java.net.URL
import java.util.*

/**
 * Controller zur Darstellung eines gebauten EnergyProducers.
 *
 * @author Gaertner
 */
class ConstructedController : GameUIControllerBase() {

    @FXML
    private lateinit var level: Label

    @FXML
    private  lateinit var image: Image

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // keine Bindings oder Callbacks in diesem Controller noetig
    }
}