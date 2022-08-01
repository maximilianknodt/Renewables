package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import javafx.fxml.FXML
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

/**
 * TODO: documentation
 *
 * @author Gaertner
 */
class GameUIController : GameUIControllerBase(), Initializable {

    @FXML
    private lateinit var constructables: List<ConstructableController>

    @FXML
    private lateinit var resources: ResourcesController

    @FXML
    private lateinit var notifications: List<NotificationController>

    @FXML
    private lateinit var  constructed: List<ConstructedController>


    override fun initData(model: GameModel) {
        super.model = model
        // TODO: initData Aufrufe an alle Sub-Controller deligieren
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // TODO: UI-Elemente, die kontrolliert oder beschrieben werden sollen setzen
        // TODO: Bindings durchführen
    }
}