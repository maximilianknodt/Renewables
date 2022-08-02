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
 * TODO: documentation
 *
 * @author Kirkesler
 */
class RouteController() : Router {
    private val stage: Stage = Stage()

    override fun showScene(route: Route) {
        val fxmlLoader = FXMLLoader(RenewablesApp::class.java.getResource(route.path))
        val scene = Scene(fxmlLoader.load(), 720.0, 540.0)

        var controller = fxmlLoader.getController<Initializable>()

        when(route) {
            Route.MENU -> (controller as MainMenuController).initData(MenuModel())
            Route.GAME -> (controller as GameUIController).initData(GameModel())
            Route.GAMEOVER -> (controller as GameUIController).initData(GameModel())
        }

        stage.title = route.stageTitle
        stage.scene = scene
        stage.show()
    }
}