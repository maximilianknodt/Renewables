package de.hsos.gaertner_kirkesler_knodt.menu

import javafx.fxml.FXML
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

/**
 * Controller fuer das Hauptmenue
 *
 * @author Gaertner, Kirkesler
 */
class MainMenuController : Initializable {

    private lateinit var model: MenuModel

    fun initData(data: MenuModel) {
        this.model = data
    }

    @FXML
    private fun onStart(){
        model.startGame()
    }

    @FXML
    private fun onEnd(){
        model.endApplication()
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) { }
}