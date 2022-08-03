package de.hsos.gaertner_kirkesler_knodt.game.ui

import de.hsos.gaertner_kirkesler_knodt.RenewablesApp
import de.hsos.gaertner_kirkesler_knodt.game.production.EnergyProducer
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.net.URL
import java.util.*

/**
 * Controller zur Darstellung eines gebauten EnergyProducers.
 *
 * @author Gaertner
 */
class ConstructedController(
    private val energyProducer: EnergyProducer
) : GameUIControllerBase() {

    @FXML
    private lateinit var level: Label

    @FXML
    private  lateinit var image: ImageView

    /**
     * Initialisiert die Darstellung des [energyProducer].
     */
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        level.text = energyProducer.level.toString()
        image.image = Image(RenewablesApp::class.java.getResource(energyProducer.imgPath).toString())
    }
}