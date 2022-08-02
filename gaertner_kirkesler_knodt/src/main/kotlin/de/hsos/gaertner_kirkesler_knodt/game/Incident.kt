package de.hsos.gaertner_kirkesler_knodt.game

import de.hsos.gaertner_kirkesler_knodt.game.IncidentType.*

/**
 * Diese Klasse stellt einen Vorfall dar. Sie enthaelt eine Builder-Klasse
 * zur Erstellung von Vorfaellen.
 *
 * Builder-Pattern: vgl. https://stackoverflow.com/questions/36140791/how-to-implement-builder-pattern-in-kotlin
 *
 * @param type Art des Vorfalls
 * @param severity Schwere des Vorfalls
 *
 * @author Kirkesler
 */
class Incident private constructor(
    val type: IncidentType?,
    val severity: Severity
    ) {

    /*  Hier wird ein sekundaerer Konstruktor implementiert, welcher als Parameter ein Objekt
        der internen Klasse 'Builder' erhaelt. Es wird anschließend an den Aufruf des primaeren Konstruktors
        delegiert und die Werte aus dem Builder-Objekt gesetzt
    */
    private constructor(builder: Builder) : this(builder.type, builder.severity)

    /**
     * Gibt den Anteil der Bevoelkerung zurueck, die durch diesen Vorfall dahingerafft wird. Der Anteil basiert auf
     * der Schwere und der Art des Ereignisses.
     *
     * @return Anteil der Bevoelkerung, die dahingerafft wird
     *
     * @author Gaertner
     */
    fun proportionOfPopulationKilled(): Double {
        return when(type){
            EARTHQUAKE -> 0.15 * severity.avgPercentage()
            ERUPTION -> 0.1 * severity.avgPercentage()
            APOCALYPSE -> 0.4 * severity.avgPercentage()
            TZUNAMI -> 0.2 * severity.avgPercentage()
            GIANT_LIZARD -> 0.3
            else -> 0.0
        }
    }


    class Builder {
        var type: IncidentType? = null
            private set
        var severity: Severity = Severity.LOW
            private set

        // Ruft die Methode getRandom() des Enums 'IncidentType' auf, um ein zufaelliges Ereignis zu erzeugen
        fun randomType() = apply {
            this.type = IncidentType.getRandom()
        }

        // Bestimmt die Schwere des Vorfalls. Wird im Enum mit Hilfe der Rundenzahl zufällig gewaehlt
        fun withSeverityForRound(index: Int) = apply {
            this.severity = Severity.getSeverity(index)
        }

        // Ruft den sekundaeren Konstruktor der Klasse aeußeren Klasse auf und übergibt sich selbst
        fun build() = Incident(this)
    }
}
