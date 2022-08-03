package de.hsos.gaertner_kirkesler_knodt.game.production

import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructable
import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructed
import de.hsos.gaertner_kirkesler_knodt.game.production.state.Constructing
import de.hsos.gaertner_kirkesler_knodt.game.production.state.ProducerState
import kotlin.math.roundToInt

/**
 * Abstrakte Klasse die den erbenden Konstruktklassen entsprechende Methoden vorschreibt
 *
 * @author Knodt
 */
abstract class EnergyProducer {
    abstract val cost: Int
    var level: Int = 0
        set(value) {
            if(value < 0) field = this.maxLevel
            if(value > this.maxLevel) field = this.maxLevel
        }

    private val maxLevel = 3
    var state: ProducerState = Constructable()

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
     * Aendert den Produktionszustand des Konstrukts in Constructing und dessen Level
     */
    public fun construct(){
        this.level++
        this.state.nextState()
    }

    /**
     * Aendert das Level des Konstrukts
     */
    public fun levelUp(){
        if(this.state is Constructed){
            if(this.level < this.maxLevel) {
                this.level++
            }
        }
    }

    /**
     * Aendert den Produktionszustand des Konstrukts in Constructed
     */
    public fun finishConstructing(){
        if(this.state is Constructing) {
            this.state.nextState()
        }
    }

    /**
     * Reduziert das Level des Konstrukts je nach Staerke des Vorfalls
     *
     * @param inc Vorfall
     */
    protected fun severityImpact(inc: Incident): Int{
        return (this.level * inc.severity.avgPercentage()).roundToInt()
    }
}