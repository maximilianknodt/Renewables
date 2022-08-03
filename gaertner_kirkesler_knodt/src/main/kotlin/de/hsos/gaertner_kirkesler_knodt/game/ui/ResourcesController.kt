package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.Image
import java.net.URL
import java.util.*

/**
 * Controller fuer die View, welche die verfuegbaren Resourcen anzeigt.
 *
 * @author Gaertner
 */
class ResourcesController : GameUIControllerBase() {

    @FXML
    private lateinit var energyConsumption: Label

    @FXML
    private lateinit var energyProduction: Label

    @FXML
    private lateinit var population: Label

    @FXML
    private lateinit var money: Label

    /**
     * Fuehrt die Bindings zwischen den Model- und View-Komponenten aus, nachdem das Model dem Controller bekannt
     * gemacht wurde. Die Bindings werden ausgefuehrt, nachdem die View-Komponenten initialisiert wurden.
     *
     * @param model Das Model, das dem Controller bekannt gemacht werden soll und die Daten traegt.
     */
    override fun initData(model: GameModel) {
        this.model = model
        model.resources.addListener { _, _, newValue ->
            energyConsumption.text = "- " +  newValue.energyConsumption.toString()
            energyProduction.text = "+ " + newValue.energyProduction.toString()
            population.text = newValue.population.toString()
            money.text = newValue.money.toString()
        }
    }

    /**
     * Da in der Resourcen-View keine weiteren View-Komponenten initialisiert werden muessen, bis auf die vom
     * FXMLLoader zugewiesenen Labels, wird in der eigenen Realisierung der initialize-Methode keine Aktion durchgefuehrt.
     */
    override fun initialize(location: URL?, resources: ResourceBundle?) {}
}