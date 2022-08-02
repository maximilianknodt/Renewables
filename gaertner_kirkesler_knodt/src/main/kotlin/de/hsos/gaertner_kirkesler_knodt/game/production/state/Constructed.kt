package de.hsos.gaertner_kirkesler_knodt.game.production.state

/**
 * Die Klasse Constructed repraesentiert den Zustand, des Konstrukt wenn der Bau fertig gestellt wurde
 *
 * @author Knodt
 */
class Constructed : ProducerState {
    init {
        println("Energy Producer ist constructed")
    }

    /**
     * Aendert den Zustand des Konstrukts
     *
     * @return Das Konstrukt kann wieder gebaut werden
     */
    override fun nextState(): ProducerState {
        return Constructable()
    }

}