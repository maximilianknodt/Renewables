package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import javafx.fxml.FXML
import javafx.scene.control.Label
import java.net.URL
import java.util.*

/**
 * TODO: documentation
 *
 * @author Gaertner
 */
class NotificationController : GameUIControllerBase() {

    private lateinit var notification: Notification

    @FXML
    private lateinit var title: Label

    @FXML
    private lateinit var message: Label

    override fun initData(model: GameModel) {
        TODO("Not yet implemented")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        TODO("bindings")
    }

    @FXML
    private fun onClose(){
        model.closeNotification(notification)
    }
}