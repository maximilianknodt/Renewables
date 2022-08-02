package de.hsos.gaertner_kirkesler_knodt.routing
import de.hsos.gaertner_kirkesler_knodt.BaseModel
import de.hsos.gaertner_kirkesler_knodt.RenewablesApp
import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.ui.GameUIController
import de.hsos.gaertner_kirkesler_knodt.menu.MainMenuController
import de.hsos.gaertner_kirkesler_knodt.menu.MenuModel
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.stage.Stage
/**
 * Diese Klasse stellt einen Vorfall dar. Sie enthaelt eine Builder-Klasse
 * zur Erstellung von Vorfaellen.
 *
 * Builder-Pattern: vgl. https://stackoverflow.com/questions/36140791/how-to-implement-builder-pattern-in-kotlin
 *
 * @param type Art des Vorfalls
 * @param severity Schwere des Vorfalls
 *
 * @author Kirkesler
 */
class RouteController() : Router {
    lateinit var stage: Stage
    init {
        this.stage = Stage()
    }

    override fun showScene(route: Route) {
        val fxmlLoader = FXMLLoader(RenewablesApp::class.java.getResource(route.path))
        val scene = Scene(fxmlLoader.load(), 720.0, 540.0)
        lateinit var model: BaseModel

        when(route) {
            Route.MENU -> {
                model = MenuModel()
                val controller: MainMenuController = fxmlLoader.getController<MainMenuController>()
                controller.initData(model)
            }
            Route.GAME -> {
                model = GameModel()
                val controller: GameUIController = fxmlLoader.getController<GameUIController>()
                controller.initData(model)
            }
            Route.GAMEOVER -> {
                // TODO: set correct Controller
                model = GameModel()
                val controller: GameUIController = fxmlLoader.getController<GameUIController>()
                controller.initData(model)
            }
        }

        stage.title = route.stageTitle
        stage.scene = scene
        stage.show()
    }
}