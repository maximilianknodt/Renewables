package de.hsos.gaertner_kirkesler_knodt.game.population

import de.hsos.gaertner_kirkesler_knodt.game.incident.Incident
import kotlin.math.ln
import kotlin.math.pow

/**
 * Algorithmus zur Berechnung der Bevoelkerungsentwicklung, der mit einer Exponentialfunktion arbeitet.
 * Da Ereignisse das Bevoelkerungswachstum beeinflussen koennen, wird der Rundenzaehler nicht direkt als x-Wert genutzt,
 * sondern auf Basis der aktuellen Bevoelkerungszahl gerechnet.
 *
 * @param base Basis der Exponentialfunktion
 * @param startingPopulation Anfangswert fuer Exponentialfunktion
 *
 * @author Gaertner
 */
class ExponentialPopulation(
    private val base: Double = 2.0,
    private val startingPopulation: Int = 100
) : PopulationAlg {

    override fun evolve(round: Int, current: Int, incident: Incident?): Int {

        if(current > 0 && base > 0){
            // Rundenzaehler Aequivalent zu aktueller Bevoelkerungszahl ermitteln
            val x = finv(current.toDouble())

            // Bevoelkerungszahl vor der Runde und nach der Runde bei Annahme des ungestoerten exponentiellen Wachstums
            val populationAtX = f(x)
            val populationAtXPlusOne = f(x + 1)
            var finalPopulation = populationAtX

            // Differenz zwischen Bevoelkerungszahl nach der Runde und vorheriger Runde
            val populationDifference = populationAtXPlusOne - populationAtX

            // gehemmtes Bevoelkerungswachstum sowie Bestandsdezimierung auf Basis von Vorfall
            if(incident != null){
                val popFactor = 1.0 - incident.proportionOfPopulationKilled()
                finalPopulation *= popFactor
                finalPopulation += populationDifference * popFactor
            }

            return finalPopulation.toInt()

        } else {
            return startingPopulation
        }
    }

    /**
     * Berechnet eine Exponentialfunktion an der Stelle x.
     * Exponentialwachstum: f(x) = a * b^(c*x + d) + e
     * a = 1, b = base, c = 1, d = 0, e = startingPopulation
     *
     * @param x x-Wert
     * @return Wert der Exponentialfunktion an der Stelle x
     */
    private fun f(x: Double): Double {
        return  base.pow(x) + startingPopulation
    }

    /**
     * Berechnet f(x) invers, ermittelt x-Wert fuer gegebenen y-Achsenabschnitt.
     *
     * @param y y-Achsenabschnitt
     * @return x-Wert fuer gegebenen y-Achsenabschnitt
     */
    private fun finv(y: Double): Double {
        return ln(y - startingPopulation) / ln(base)
    }

}