package de.hsos.gaertner_kirkesler_knodt.game
/**
 * Diese Datenklasse stellt eine einzelne Benachrichtigung dar.
 *
 * @param id ID der Benachrichtigung
 * @param title Titel der Benachrichtigung
 * @param desc Beschreibung der Benachrichtigung
 *
 * @author Kirkesler
 */
data class Notification(
    val id: Int,
    val title: String,
    val desc: String,
)

