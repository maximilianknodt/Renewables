package de.hsos.gaertner_kirkesler_knodt.game.population

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

            // Bevoelkerungszahl vor der Runde
            val populationAtX = f(x)

            // Bevoelkerungszahl nach der Runde bei Annahme des ungestoerten exponentiellen Wachstums
            val populationAtXPlusOne = f(x + 1)

            // Differenz zwischen Bevoelkerungszahl nach der Runde und vorheriger Runde
            val populationDifference = populationAtXPlusOne - populationAtX

            // TODO: population decrease based on incident

            return populationAtXPlusOne.toInt()

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
     */
    private fun f(x: Double): Double {
        return  base.pow(x) + startingPopulation;
    }

    /**
     * Berechnet f(x) invers, ermittelt x-Wert fuer gegebenen y-Achsenabschnitt.
     *
     * @param y y-Achsenabschnitt
     */
    private fun finv(y: Double): Double {
        return ln(y - startingPopulation) / ln(base);
    }

}