package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.Notification
import de.hsos.gaertner_kirkesler_knodt.game.production.EnergyProducer
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing
import javafx.beans.value.ChangeListener
import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

/**
 * Zentraler Controller der Spiel-Seite. Hier werden die dynamisch aufgrund von Listeneintraegen im Model erzeugten
 * Controller erstellt und verwaltet. Der FXMLLoader kann den einzelnen Controller [resourcesController] mithilfe der
 * FXML-Annotation selbststaendig als geschachtelten Controller zuweisen. Die weiteren Controller fuer Listenelemente
 * werden in Listen auf Basis des [model] gespeichert.
 *
 * @author Gaertner
 */
class GameUIController : GameUIControllerBase() {

    private val gameFieldSize: Pair<Int, Int> = Pair(900, 600)

    @FXML // geschachtelter Controller wird von FXML-Loader instanziiert
    private lateinit var resourcesController: ResourcesController

    private val constructableController: MutableList<ConstructableController> = ArrayList()
    private val notificationController: MutableList<NotificationController> = ArrayList()
    private val constructedController: MutableList<ConstructedController> = ArrayList()

    @FXML
    private lateinit var constructableContainer: HBox

    @FXML
    private lateinit var notificationContainer: VBox

    @FXML
    private lateinit var constructedContainer: AnchorPane

    @FXML
    private lateinit var nextRoundButton: Button

    @FXML
    private lateinit var endGameButton: Button

    /**
     * Bekanntmachen des GameModels [model] beim Controller. Da nun die Model-Daten bekannt sind, koennen hier auch die
     * Bindings durchgefuehrt und der Initialzustand der View auf Basis der vorhandenen Daten gesetzt werden.
     *
     * @param model Das GameModel, das bekannt gemacht werden soll.
     */
    override fun initData(model: GameModel) {
        super.model = model

        // Da das Model nun bekannt gemacht wurde, koennen jetzt die Bindings zu den Daten aus diesem gesetzt werden
        initBindings()

        // Zu Beginn sollen bereits alle EnergieProduzenten fuer den Bau angezeigt werden
        for (energyProducer in model.energyProducer) {
            showConstructable(energyProducer)
        }

        // initData Aufrufe an alle Sub-Controller deligieren
        constructableController.forEach { it.initData(model) }
        constructedController.forEach { it.initData(model) } // TODO: wird nie aufgerufen?
        notificationController.forEach { it.initData(model) } // TODO: wird nie aufgerufen?
        resourcesController.initData(model)

        // Model registriert sich bei der Simulation
        model.register()
    }

    /**
     * Wird vom FXMLLoader aufgerufen, sobald die View geladen wurde. Hier wird die Callback-Methode fuer den Button
     * gesetzt, der die naechste Runde starten soll. Dieser Befehl wird an die Spiellogik im [model] delegiert.
     * Dasselbe gilt fuer den Ende-Button, der im [model] die Methode [GameModel.endGame] aufruft.
     */
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        nextRoundButton.onMouseClicked = EventHandler {
            model.simulateRound()
        }
        endGameButton.onMouseClicked = EventHandler {
            model.endGame()
        }
    }

    /**
     * Initialisiert die Bindings / Listener zu den Daten des Models.
     * Die Verbindungen zu der [ObservableList] energyProducer und der [ObjectProperty] notificationList sind relevant,
     * da es sich eine Aenderung in diesen Daten auf die Menge der Kinder der View-Komponenten [constructableContainer]
     * und [notificationContainer] auswirkt.
     */
    private fun initBindings(){
        // Befuellen der Controller-Listen auf Basis der Model-Daten
        model.energyProducer.addListener( ListChangeListener{ change ->
                    // Darstellung der gesetzten Energieproduzenten zuruecksetzen
                    constructedContainer.children.clear()
                    constructedController.clear()

                    // Energieproduzenten anzeigen
                    for(energyProducer in model.energyProducer) {
                        if(energyProducer.state is Constructed || energyProducer.state is Constructing){
                            showConstructed(energyProducer)
                        }
                    }
            }
        )

        model.notifications.addListener(
            ChangeListener { _, _, newValue ->
                // Darstellung der Benachrichtigungen zuruecksetzen
                println("notifications changed")
                notificationContainer.children.clear()
                notificationController.clear()

                // Benachrichtigungen anzeigen
                for (notification in newValue.notifications.values) {
                    showNotification(notification)
                }
            }
        )
    }

    /**
     * Laedt die View fuer einen konstruierbaren Energieproduzenten, erstellt den zugehoerigen Controller und stellt
     * diesen in der horizontalen Liste der konstruierbaren Energieproduzenten dar.
     */
    private fun showConstructable(energyProducer: EnergyProducer, constructable: Boolean = true) {
        val loader = FXMLLoader(javaClass.getResource("constructableCard.fxml"))
        val controller = ConstructableController(energyProducer)
        controller.initData(model)
        loader.setController(controller)
        constructableContainer.children.add(loader.load())
        constructableController.add(controller)
    }

    /**
     * Laedt die View fuer einen auf dem Spielfeld platzierten konstruierten EnergieProduzenten und zeigt diesen an.
     */
    private fun showConstructed(energyProducer: EnergyProducer) {
        val loader = FXMLLoader(javaClass.getResource("constructed.fxml"))
        val controller = ConstructedController(energyProducer)
        controller.initData(model)
        loader.setController(controller)
        val node = loader.load<AnchorPane>()
        node.layoutX = energyProducer.position.first * gameFieldSize.first
        node.layoutY = energyProducer.position.second * gameFieldSize.second
        constructedContainer.children.add(node)
        constructedController.add(controller)
        println("showConstructed: ${energyProducer.name}")
    }

    /**
     * Laedt die View fuer eine Benachrichtigung in der Benachrichtigungsliste und zeigt diese an.
     */
    private fun showNotification(notification: Notification) {
        val loader = FXMLLoader(javaClass.getResource("notification.fxml"))
        val controller = NotificationController(notification)
        controller.initData(model)
        loader.setController(controller)
        notificationContainer.children.add(loader.load())
        notificationController.add(controller)
    }
}