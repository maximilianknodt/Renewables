package de.hsos.gaertner_kirkesler_knodt.game.production.state

/**
 * Die Klasse Constructing repraesentiert den Zustand, des Konstrukt waehrend es sich im Bau befindet
 *
 * @author Knodt
 */
class Constructing : ProducerState {
    init {
        // println("Energy Producer is being constructed!")
    }

    /**
     * Aendert den Zustand des Konstrukts
     *
     * @return Das Konstrukt ist fertig gebaut
     */
    override fun nextState(): ProducerState {
        return Constructed()
    }
}