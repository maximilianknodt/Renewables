package de.hsos.gaertner_kirkesler_knodt.game
import java.lang.Math.floor
import kotlin.random.Random
/**
 * Dieses Enum enthaelt verschiedene Level.
 *
 * @param min Unterer Schwellenwert
 * @param max Oberer Schwellenwert
 *
 * @author Kirkesler
 */
enum class Severity(
    var min: Int? = 0,
    var max: Int? = 10
) {
    // Zuweisung von Schwellenwerten fuer Schwere der Vorfaelle verschiedener Level
    LOW(0,15),
    MEDIUM(16,30),
    HIGH(31,50),
    CATASTROPHE(51, 70),
    GAU(71, 99);

    init {
        require(min in 0..100 && max in 0..100) {
            "min and max must be in range between 0% and 100%"
        }
    }

    fun avgPercentage(): Double {
        return (min!! + max!!) / 2.0 / 100.0
    }

    companion object {
        // Bestimmt die Schwere des Vorfalls mit Hilfe der Rundenzahl
        // 'floor((index/2))' bis 'index - 1'
        public fun getSeverity(index: Int): Severity {
            val min: Int = kotlin.math.floor((index / 2).toDouble()).toInt()
            val max: Int = index - 1
            return Severity.values()[Random.nextInt(min, max)]
        }
    }
}

