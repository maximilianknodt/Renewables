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
    abstract val position: Pair<Double, Double>

    abstract val cost: Int
    abstract val energy: Array<Int>

    protected var level: Int = 0
        set(value) {
            field = if(value < 0) 0
                else if(value > this.maxLevel) this.maxLevel
                else value
        }

    private val maxLevel = 3
    var state: ProducerState = Constructable()

    /**
     * Zerstoert das Konstrukt je nach Staerke und Art des Vorfalls
     *
     * @param incident ereigneter Vorfall
     */
    abstract fun destroy(incident: Incident)

    /**
     * Gibt den aktuellen Energieertrag des Konstrukts abhaengig vom Level zurueck
     *
     * @return aktueller Energieertrag des Konstrukts
     */
    fun activeEnergyOutput(): Int {
        return this.energy[this.level]
    }

    /**
     * Gibt den gesamten Energieetrag des Konstrukts zurueck, der durch das naechste Level erreicht wird
     *
     * @return gesamt Energieetrag des Konstrukts fuer nachstes Level
     */
    fun nextLevelEnergyOutput(): Int {
        if(this.level < this.maxLevel) {
            return this.energy[this.level++]
        }
        return this.energy[this.maxLevel]
    }

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
     * Setzt den Zustand auf Constructable und das Level des Singletons auf 0 zurueck.
     * Da die Werte [cost] und [energy] vom Level abhaengig sind, muessen diese nicht separat geaendert werden.
     */
    fun resetObject(){
        this.level = 0
        this.state = Constructable()
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