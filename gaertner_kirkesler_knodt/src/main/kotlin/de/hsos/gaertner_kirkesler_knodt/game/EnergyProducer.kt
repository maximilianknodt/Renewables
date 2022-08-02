package de.hsos.gaertner_kirkesler_knodt.game

/**
 * Abstrakte Klasse die den erbenden Konstruktklassen entsprechende Methoden vorschreibt
 *
 * @author Knodt
 */
open abstract class EnergyProducer {
    abstract var level: Int
    abstract var cost: Int
    protected var state: ProducerState = Constructable()

    /**
     * Gibt den Energieertrag des Konstrukts zurueck
     *
     * @return Energieertrag
     */
    abstract fun energyOutput(): Int

    /**
     * Gibt die Kosten des Konstrukt zurueck
     *
     * @return Kosten des Konstrukts
     */
    abstract fun buildingCosts(): Int

    /**
     * Zerstoert das Konstrukt je nach Staerke und Art des Vorfalls
     *
     * @param incident ereigneter Vorfall
     */
    abstract fun destroy(incident: Incident)

    /**
     * Aendert den Produktionszustand des Konstrukts
     */
    abstract fun construct()
}