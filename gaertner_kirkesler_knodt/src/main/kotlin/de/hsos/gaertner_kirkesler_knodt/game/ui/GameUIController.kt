package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.Notification
import de.hsos.gaertner_kirkesler_knodt.game.NotificationList
import de.hsos.gaertner_kirkesler_knodt.game.production.EnergyProducer
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
import javafx.beans.value.ChangeListener
import javafx.collections.ListChangeListener
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

/**
 * TODO: documentation
 *
 * @author Gaertner
 */
class GameUIController : GameUIControllerBase(), Initializable {

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

    override fun initData(model: GameModel) {
        super.model = model

        for (energyProducer in model.energyProducer) {
            showConstructable(energyProducer)
        }

        // Befuellen der Controller-Listen auf Basis der Model-Daten
        model.energyProducer.addListener(
            ListChangeListener<EnergyProducer> {
                fun onChanged(c: ListChangeListener.Change<out EnergyProducer>?) {
                    // Darstellung der Energieproduzenten zuruecksetzen
                    constructableContainer.children.clear()
                    constructableController.clear()
                    constructedContainer.children.clear()
                    constructedController.clear()

                    // Energieproduzenten anzeigen
                    for(energyProducer in model.energyProducer) {
                        when(energyProducer.state){
                            is Constructable -> showConstructable(energyProducer)
                            else -> showConstructed(energyProducer)
                        }
                    }
                }
            }
        )

        // "filigrane" Methode mit Filterung auf exakte Änderungen
        /*model.energyProducer.addListener(
            ListChangeListener<EnergyProducer> {
                fun onChanged(c: ListChangeListener.Change<out EnergyProducer>?) {
                    print("changed")
                    while(c?.next() == true){
                        if(c.wasUpdated()){
                            print("updated")

                            // Iterieren ueber alle EnergyProducer, die sich veraendert haben
                            for(index in c.from..c.to){
                                val energyProducer = c.list[index]
                                when(energyProducer.state){
                                    is Constructable -> {
                                        // falls vorher constructed oder constructing war, muss der Controller und das View entfernt werden
                                        showConstructable(energyProducer)
                                    }

                                }
                            }
                        }
                    }
                }
            }
        )*/

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

        // initData Aufrufe an alle Sub-Controller deligieren
        resourcesController.initData(model)
        constructableController.forEach { it.initData(model) }
        constructedController.forEach { it.initData(model) } // TODO: wird nie aufgerufen?
        notificationController.forEach { it.initData(model) } // TODO: wird nie aufgerufen?
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // TODO: UI-Elemente, die kontrolliert oder beschrieben werden sollen setzen
        // TODO: Bindings durchführen

        println("GameUIController.initialize()")
    }

    private fun showConstructable(energyProducer: EnergyProducer) {
        val loader = FXMLLoader(javaClass.getResource("/fxml/game/constructableCard.fxml"))
        val controller = loader.getController<ConstructableController>()
        controller.initData(model) // TODO: EnergyProducer als Parameter angeben?
        constructableContainer.children.add(loader.load())
        constructableController.add(controller)
    }

    private fun showConstructed(energyProducer: EnergyProducer) {
        val loader = FXMLLoader(javaClass.getResource("/fxml/game/constructed.fxml"))
        val controller = loader.getController<ConstructedController>()
        controller.initData(model)
        constructedContainer.children.add(loader.load())
        constructedController.add(controller)
    }

    private fun showNotification(notification: Notification) {
        val loader = FXMLLoader(javaClass.getResource("/fxml/game/notification.fxml"))
        val controller = loader.getController<NotificationController>()
        controller.initData(model) // TODO: ID des Notifications-Objekts angeben?
        notificationContainer.children.add(loader.load())
        notificationController.add(controller)
    }
}