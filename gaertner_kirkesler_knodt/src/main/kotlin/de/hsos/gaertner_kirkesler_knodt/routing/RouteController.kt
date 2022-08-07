package de.hsos.gaertner_kirkesler_knodt.routing
import de.hsos.gaertner_kirkesler_knodt.RenewablesApp
import de.hsos.gaertner_kirkesler_knodt.game.GameModel
import de.hsos.gaertner_kirkesler_knodt.game.ui.GameUIController
import de.hsos.gaertner_kirkesler_knodt.gameover.GameOverController
import de.hsos.gaertner_kirkesler_knodt.gameover.GameOverModel
import de.hsos.gaertner_kirkesler_knodt.menu.MainMenuController
import de.hsos.gaertner_kirkesler_knodt.menu.MenuModel
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

/**
 * Diese Klasse implementiert das Interface 'Router' und die dort deklarierte Methode
 * 'showScene()'. Bei Aufruf der Methode 'showScene()' wird die Zielroute übergeben.
 * Anschließend wird das entsprechende FXML geladen und der Stage zugewiesen.
 *
 * @author Kirkesler
 */
object RouteController : Router {
    // TODO: In Modellierung uebernehmen
    private val stage: Stage = Stage()

    /**
     * Implementation des Interfaces 'Router'. Methode dient zur Navigation zwischen
     * unterschiedlichen Szenen.
     *
     * @param route Zielroute als Instanz des Enums 'Route'
     * @author Kirkesler
     */
    override fun showScene(route: Route) {
        val fxmlLoader = FXMLLoader(RenewablesApp::class.java.getResource(route.path))
        val scene = Scene(fxmlLoader.load(), 900.0, 750.0)
        val controller = fxmlLoader.getController<Initializable>()

        when(route) {
            Route.MENU -> (controller as MainMenuController).initData(MenuModel(this))
            Route.GAME -> (controller as GameUIController).initData(GameModel(this))
            Route.GAMEOVER -> (controller as GameOverController).initData(GameOverModel(this))
        }

        stage.title = route.stageTitle
        stage.scene = scene
        stage.icons.add(Image(RenewablesApp::class.java.getResourceAsStream("assets/atomic.png")))
        //stage.setResizable(false)
        stage.show()
    }
}