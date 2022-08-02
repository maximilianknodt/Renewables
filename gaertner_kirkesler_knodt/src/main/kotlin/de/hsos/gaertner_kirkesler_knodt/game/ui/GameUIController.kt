package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.NotificationList
import javafx.beans.value.ChangeListener
import javafx.collections.ListChangeListener
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

        // initData Aufrufe an alle Sub-Controller deligieren
        constructables.forEach { it.initData(model) }
        resources.initData(model)
        notifications.forEach { it.initData(model) }
        constructed.forEach { it.initData(model) }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // TODO: UI-Elemente, die kontrolliert oder beschrieben werden sollen setzen
        // TODO: Bindings durchführen

        // add listeners on data here
        model.energyProducer.addListener(
            ListChangeListener{
                print("changed energy producer")
                // TODO: update der Liste --> update der UI & Controller in this.constructables und this.constructed
            }
        )

        /*model.notifications.addListener {
            ChangeListener<NotificationList> { _, oldValue, newValue ->
                print("changed notifications: $oldValue -> $newValue")
                // TODO: update der NotificationList --> update der UI & Controller in this.notifications
            }
        }*/

        /* // remove this old stuff:
        model.energyProducer.addListener(
            ListChangeListener<EnergyProducer> {
                fun onChanged(c: ListChangeListener.Change<out EnergyProducer>?) {
                    print("changed")
                    while(c?.next() == true){
                        if(c.wasUpdated()){
                            print("updated")
                            c.get
                        }
                    }
                }
            }
        )*/
    }
}