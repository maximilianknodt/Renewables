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
    abstract val name: String
    abstract val imgPath: String
    abstract val cost: Int
    var level: Int = 0
        set(value) {
            field = if(value < 0) 0
                else if(value > this.maxLevel) this.maxLevel
                else value
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
    fun construct(){
        level += 1
        state = state.nextState()
    }

    /**
     * Aendert das Level des Konstrukts
     */
    fun levelUp(){
        if(this.state is Constructed){
            if(this.level < this.maxLevel) {
                this.level++
            }
        }
    }

    /**
     * Aendert den Produktionszustand des Konstrukts in Constructed
     */
    fun finishConstructing(){
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