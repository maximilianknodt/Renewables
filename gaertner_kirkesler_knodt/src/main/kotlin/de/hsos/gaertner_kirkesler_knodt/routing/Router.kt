package de.hsos.gaertner_kirkesler_knodt.routing
/**
 * Interface mit einer einzigen Methode die zur Navigation zwischen verschiedene Szenen
 * dient.
 *
 * @author Kirkesler
 */
interface Router {
    /**
     * Diese Methode dient zur Navigation zwischen unterschiedlichen Szenen.
     *
     * @param route Zielroute als Instanz des Enums 'Route'
     *
     * @author Kirkesler
     */
    public fun showScene(route: Route)
}