package de.hsos.gaertner_kirkesler_knodt.game.incident
import kotlin.random.Random

/**
 * Dieses Enum enthaelt verschiedene Arten von Vorfaellen.
 *
 * @author Kirkesler
 */
enum class IncidentType {
    EARTHQUAKE,
    ERUPTION,
    APOCALYPSE,
    FIRE,
    UFO,
    TZUNAMI,
    GIANT_LIZARD;

    // companion object analog zu Java als 'static' zu verstehen
    companion object {
        // statisches 'caching' der Incidents als Array und Groeße des Arrays.
        // (s. https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum)
        private val enums: Array<IncidentType> = IncidentType.values()
        private val size: Int = enums.size

        // zufaelliges Auswaehlen eines Incidents
        fun getRandom(): IncidentType {
            return enums[Random.Default.nextInt(size)]
        }
    }
}