package de.hsos.gaertner_kirkesler_knodt

import de.hsos.gaertner_kirkesler_knodt.routing.RouteController
import de.hsos.gaertner_kirkesler_knodt.routing.Router
/**
 * Abstrakte Klasse 'BaseModel' dient als Basis für weitere Models.
 * Instanziiert einen 'RouteController' für die Navigation zwischen Szenen.
 *
 * @author Kirkesler
 */
open class BaseModel(
    protected open var router: Router
) {
}