package de.hsos.gaertner_kirkesler_knodt.game
/**
 * Diese Klasse hat eine Liste von Benachrichtigungen als Singleton.
 *
 * @param notifications HashMap der bestehenden Benachrichtungen
 *
 * @author Kirkesler
 */

// TODO: Datenklasse oder nicht?
class NotificationList() {
    var notifications: LinkedHashMap<Int, Notification>

    init {
        notifications = LinkedHashMap<Int, Notification>()
    }
    public fun clear() {
        this.notifications?.clear()
    }
    public fun close(id: Int) {
        this.notifications?.remove(id)
    }
    public fun add(notification: Notification) {
        this.notifications?.put(notification.id, notification)
    }
    public fun delete() {
        //TODO: not implemented yet
    }
}
