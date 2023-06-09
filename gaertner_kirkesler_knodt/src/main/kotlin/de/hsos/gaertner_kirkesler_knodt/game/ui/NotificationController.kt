package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.notification.Notification
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import java.net.URL
import java.util.*

/**
 * Controller fuer eine Benachrichtigung, verbunden mit der View "notification.fxml".
 *
 * @author Gaertner
 */
class NotificationController(
    private val notification: Notification,
) : GameUIControllerBase() {

    @FXML
    private lateinit var closeButton: Button

    @FXML
    private lateinit var title: Label

    @FXML
    private lateinit var message: Label

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        title.text = notification.title
        message.text = notification.desc
        closeButton.onMouseClicked = EventHandler {
            this.onClose()
        }
    }

    /**
     * Methode, die aufgerufen wird, wenn der Benutzer auf den Schliessen-Button klickt. Zu Demonstrationszwecken hier
     * direkt uber den FXMLLoader in der View bekannt gemacht, statt manuell in der initialize()-Methode zugewiesen.
     */
    @FXML
    fun onClose(){
        model.closeNotification(notification.id)
    }
}