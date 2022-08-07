package de.hsos.gaertner_kirkesler_knodt.game.notification
/**
 * Diese Klasse hat eine Liste von Benachrichtigungen als Singleton.
 *
 * @param notifications HashMap der bestehenden Benachrichtungen
 *
 * @author Kirkesler
 */
class NotificationList(
    var notifications: LinkedHashMap<Int, Notification> = LinkedHashMap<Int, Notification>()
) {

    public fun clear() {
        this.notifications?.clear()
    }
    public fun close(id: Int) {
        this.notifications?.remove(id)
    }
    public fun add(title: String, desc: String) {
        val id: Int = this.notifications.size
        this.notifications?.put(id, Notification(id, title, desc))
    }
    fun copy(): NotificationList {
        return NotificationList(notifications = notifications)
    }
}
