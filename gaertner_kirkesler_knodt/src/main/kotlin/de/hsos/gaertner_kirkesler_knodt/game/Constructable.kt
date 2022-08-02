package de.hsos.gaertner_kirkesler_knodt.game

/**
 * Die Klasse Constructable repraesentiert den Zustand, des Konstrukt wenn es gebaut werden kann
 *
 * @author Knodt
 */
class Constructable : ProducerState {
    init {
        println("Energy Producer can be build!")
    }

    /**
     * Aendert den Zustand des Konstrukts
     *
     * @return Das Konstrukt befindet sich in der Bauphase
     */
    override fun nextState(): ProducerState{
       return Constructing()
    }
}