package de.hsos.gaertner_kirkesler_knodt.game.notification
/**
 * Diese Klasse hat eine Liste von Benachrichtigungen als Singleton.
 *
 * @param notifications HashMap der bestehenden Benachrichtungen
 *
 * @author Kirkesler
 */
class NotificationList(
    var notifications: LinkedHashMap<Int, Notification> = LinkedHashMap(),
) {
    fun clear() {
        this.notifications.clear()
    }

    fun close(id: Int) {
        this.notifications.remove(id)
    }

    fun add(title: String, desc: String) {
        val id: Int = this.notifications.size
        this.notifications[id] = Notification(id, title, desc)
    }

    fun copy(): NotificationList {
        return NotificationList(notifications = notifications)
    }
}
