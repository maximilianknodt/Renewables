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
    public val id: Int,
    public val title: String,
    public val desc: String
)

