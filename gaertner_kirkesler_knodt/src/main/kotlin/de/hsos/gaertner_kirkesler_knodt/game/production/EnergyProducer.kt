package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.production.state.ProducerState
import kotlin.math.roundToInt

/**
 * Abstrakte Klasse die den erbenden Konstruktklassen entsprechende Methoden vorschreibt
 *
 * @author Knodt
 */
open abstract class EnergyProducer {
    abstract var level: Int
    abstract val cost: Int
    var state: ProducerState = Constructable()
    protected val maxLevel = 3

    /**
     * Gibt den Energieertrag des Konstrukts zurueck
     *
     * @return Energieertrag
     */
    abstract fun energyOutput(): Int

    /**
     * Zerstoert das Konstrukt je nach Staerke und Art des Vorfalls
     *
     * @param incident ereigneter Vorfall
     */
    abstract fun destroy(incident: Incident)

    /**
     * Aendert den Produktionszustand des Konstrukts und dessen Level
     */
    abstract fun construct()

    /**
     * Aendert das Level des Konstrukts
     */
    abstract fun levelUp()

    /**
     * Aendert den Produktionszustand des Konstrukts
     */
    abstract fun finishConstructing()

    /**
     * Reduziert das Level des Konstrukts je nach Staerke des Vorfalls
     *
     * @param inc Vorfall
     */
    protected fun severityImpact(inc: Incident): Int{
        return (this.level * inc.severity.avgPercentage()).roundToInt()
    }
}