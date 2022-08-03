package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.Notification
import de.hsos.gaertner_kirkesler_knodt.game.NotificationList
import de.hsos.gaertner_kirkesler_knodt.game.production.EnergyProducer
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing
import javafx.beans.value.ChangeListener
import javafx.collections.ListChangeListener
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
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

    /**
     * TODO: documentation
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
        resourcesController.initData(model)
        constructableController.forEach { it.initData(model) }
        constructedController.forEach { it.initData(model) } // TODO: wird nie aufgerufen?
        notificationController.forEach { it.initData(model) } // TODO: wird nie aufgerufen?
    }

    /**
     * TODO: documentation
     */
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // FXML-Elemente initialisieren (passiert hier durch den FXML-Loader,
        // dem die Nodes durch Kennzeichnung mit @FXML bekannt gemacht wurden)
        // TODO: nextRoundButton.onMouseClicked = { _ -> model.simulateRound() }
    }

    /**
     * TODO: documentation
     */
    private fun initBindings(){
        // Befuellen der Controller-Listen auf Basis der Model-Daten
        model.energyProducer.addListener(
            ListChangeListener<EnergyProducer> {
                fun onChanged(c: ListChangeListener.Change<out EnergyProducer>?) {
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
            }
        )

        model.notifications.addListener(
            ChangeListener<NotificationList> { _, _, newValue ->
                // Darstellung der Benachrichtigungen zuruecksetzen
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
     *
     */
    private fun showConstructable(energyProducer: EnergyProducer) {
        val loader = FXMLLoader(javaClass.getResource("/fxml/game/constructableCard.fxml"))
        val controller = ConstructableController(energyProducer)
        controller.initData(model)
        loader.setController(controller)
        constructableContainer.children.add(loader.load())
        constructableController.add(controller)
    }

    /**
     * TODO: documentation
     */
    private fun showConstructed(energyProducer: EnergyProducer) {
        val loader = FXMLLoader(javaClass.getResource("/fxml/game/constructed.fxml"))
        val controller = loader.getController<ConstructedController>()
        controller.initData(model)
        constructedContainer.children.add(loader.load())
        constructedController.add(controller)
    }

    /**
     * TODO: documentation
     */
    private fun showNotification(notification: Notification) {
        val loader = FXMLLoader(javaClass.getResource("/fxml/game/notification.fxml"))
        val controller = NotificationController(notification)
        controller.initData(model)
        loader.setController(controller)
        notificationContainer.children.add(loader.load())
        notificationController.add(controller)
    }
}