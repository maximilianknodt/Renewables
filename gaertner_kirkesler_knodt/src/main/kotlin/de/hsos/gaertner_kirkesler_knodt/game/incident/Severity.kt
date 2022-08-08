package de.hsos.gaertner_kirkesler_knodt.game.incident

/**
 * Dieses Enum enthaelt verschiedene Level.
 *
 * @param min Unterer Schwellenwert
 * @param max Oberer Schwellenwert
 *
 * @author Kirkesler
 */
enum class Severity(
    var min: Double = 0.0,
    var max: Double = 0.10,
) {
    // Zuweisung von Schwellenwerten fuer Schwere der Vorfaelle verschiedener Level
    LOW(0.0,0.15),
    MEDIUM(0.15,0.30),
    HIGH(0.30,0.50),
    CATASTROPHE(0.50, 0.95),
    GAU(0.95, 1.0);

    init {
        require(min in 0.0..1.0 && max in 0.0..1.0 && min < max) {
            "min and max must be in range between 0% and 100%"
        }
    }

    fun avgPercentage(): Double {
        return (min + max) / 2.0
    }

    companion object {
        fun getSeverity(random: Double): Severity {
            println("getSeverity $random")
            return values().first {
                random in it.min..it.max
            }
        }

    }
}

