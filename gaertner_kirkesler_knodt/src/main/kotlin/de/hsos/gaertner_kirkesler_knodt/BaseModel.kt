package de.hsos.gaertner_kirkesler_knodt

import de.hsos.gaertner_kirkesler_knodt.routing.RouteController
import de.hsos.gaertner_kirkesler_knodt.routing.Router

open class BaseModel() {
    lateinit var router: Router
    init {
        this.router = RouteController()
    }
}