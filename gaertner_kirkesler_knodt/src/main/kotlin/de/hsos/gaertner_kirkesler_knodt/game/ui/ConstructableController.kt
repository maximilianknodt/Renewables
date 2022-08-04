package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.RenewablesApp
import de.hsos.gaertner_kirkesler_knodt.game.production.EnergyProducer
import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing
import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.text.Text
import java.net.URL
import java.util.*

/**
 * Controller fuer die View "constructableCard.fxml". Zu Demonstrationszwecken wird hier kein anonymer Listener ver-
 * wendet, sondern [ConstructableController] realisiert das Interface [ListChangeListener] selbst, um bei Aenderungen
 * an der Datengrundlage im Model die Methode [onChanged] auszufuehren.
 *
 * @param prod EnergieProduzent, dessen Daten angezeigt werden sollen.
 *
 * @author Gaertner
 */
class ConstructableController(
    private val prod: EnergyProducer
) : GameUIControllerBase(), ListChangeListener<EnergyProducer> {

    @FXML
    private lateinit var name: Text

    @FXML
    private lateinit var energy: Text

    @FXML
    private lateinit var cost: Text

    @FXML
    private lateinit var image: ImageView

    @FXML
    private lateinit var card: AnchorPane

    /**
     * Macht das GameModel dem [ConstructableController] bekannt. Sobald die Daten bekannt sind, koennen die Bindings
     * durchgefuehrt werden. Fuer bessere Uebersichtlichkeit und zu Demonstrationszwecken wird hier kein anonymer
     * Listener verwendet, sondern [ConstructableController] realisiert das Interface [ListChangeListener] selbst.
     *
     * @param model Das GameModel, das dem [ConstructableController] bekannt gemacht werden soll.
     */
    override fun initData(model: GameModel) {
        this.model = model
        model.energyProducer.addListener(this)
    }

    /**
     * Wird vom FXMLLoader aufgerufen, sobald die View geladen und die Komponenten injected wurden, verbindet daraufhin
     * ebenfalls das Klick-Event auf die Karte mit der Methode [onClick].
     */
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        updateView()
        card.onMouseClicked = EventHandler { e -> onClick(e) }
    }

    /**
     * Wird ausgefuehrt, sobald auf die Karte geklickt wurde.
     */
    private fun onClick(event: MouseEvent) {
        this.model.constructOrLevelupConstructable(prod)
    }

    /**
     * Wird ausgefuehrt, sobald sich der EnergyProducer in der Liste veraendert, um die View zu aktualisieren.
     */
    private fun updateView(){
        name.text = prod.name
        energy.text = prod.nextLevelEnergyOutput().toString()
        cost.text = prod.cost.toString()
        image.image = Image(RenewablesApp::class.java.getResource(prod.imgPath).toString())
    }

    /**
     * Wird aufgerufen, sobald die ObservableList im GameModel veraendert wird. Falls eine Aenderung an dem hier
     * dargestellten [EnergyProducer] vorliegt, wird die View aktualisiert.
     */
    override fun onChanged(c: ListChangeListener.Change<out EnergyProducer>?) {
        if (c != null) {
            while(c.next()){
                if(c.wasAdded()){
                    for(prod in c.addedSubList){
                        if(prod == this.prod){
                            println("ich wurde hinzugefuegt")
                            print("ConstructableController: onChanged: ${prod.name} + ${prod.state} + ${prod.level} + ${prod.cost}")
                            updateView()
                            break
                        }
                    }
                } else if(c.wasUpdated()){
                    println("ConstructableController: wasUpdated")
                    for(i in c.from..c.to){
                        println("updated $i")
                        if(c.list[i] == prod){
                            updateView()
                            break
                        }
                    }
                }
            }
        }
    }

}
