package de.hsos.gaertner_kirkesler_knodt.game

open abstract class EnergyProducer {
    abstract var level: Int
    abstract var cost: Int
    var state: ProducerState = ProducerState()

    abstract fun energyOutput(): Int

    abstract fun cost(): Int

    abstract fun destroy(incident: Incident)

    abstract fun construct()
}