package com.space.designpatterns.behavioral

/** [EventListener] interface to listen events */
interface EventListener {
    fun onEventReceived(eventType: String, eventValue: String)
}

/** [Listener] implementation */
class Listener(private val listenerName: String) : EventListener {
    override fun onEventReceived(eventType: String, eventValue: String) {
        println(
            "$listenerName received an event:\n" +
                    "    Type:   $eventType \n" +
                    "    Value:  $eventValue \n"
        )
    }
}

/** [FileEventManager] class to handle events and subscribers.
 * It takes vararg vararg parameter events, which are Event Types to send events to */
class FileEventManager(vararg events: String) {
    private val listeners = mutableMapOf<String, MutableList<EventListener>>()

    init {
        events.forEach { event ->
            listeners[event] = mutableListOf()
        }
    }

    fun subscribe(eventType: String, listener: EventListener) = apply {
        listeners[eventType]?.add(listener)
    }

    fun unsubscribe(eventType: String, listener: EventListener) = apply {
        listeners[eventType]?.remove(listener)
    }

    fun sendEvent(eventType: String, eventValue: String) {
        listeners[eventType]?.forEach {
            it.onEventReceived(eventType, eventValue)
        }
    }
}

/** Event types */
object FileEvents {
    const val EVENT_TYPE_OPEN_FILE = "open"
    const val EVENT_TYPE_SAVE_FILE = "save"
    const val EVENT_TYPE_MODIFY_FILE = "modify"
}

fun main() {
    val listener1 = Listener("Email Listener")
    val listener2 = Listener("Push Notifications Listener")
    val listener3 = Listener("Logger Listener")
    val manager = FileEventManager(
        FileEvents.EVENT_TYPE_OPEN_FILE,
        FileEvents.EVENT_TYPE_MODIFY_FILE,
        FileEvents.EVENT_TYPE_SAVE_FILE
    )

    manager
        .subscribe(FileEvents.EVENT_TYPE_OPEN_FILE, listener1)
        .subscribe(FileEvents.EVENT_TYPE_SAVE_FILE, listener1)
        .subscribe(FileEvents.EVENT_TYPE_MODIFY_FILE, listener2)
        .subscribe(FileEvents.EVENT_TYPE_OPEN_FILE, listener3)
        .subscribe(FileEvents.EVENT_TYPE_MODIFY_FILE, listener3)

    manager.sendEvent(FileEvents.EVENT_TYPE_OPEN_FILE, "File opened")
    manager.sendEvent(FileEvents.EVENT_TYPE_MODIFY_FILE, "Changes applied to file")

    manager.unsubscribe(FileEvents.EVENT_TYPE_MODIFY_FILE, listener2)

    manager.sendEvent(FileEvents.EVENT_TYPE_MODIFY_FILE, "More changes applied to file")
    manager.sendEvent(FileEvents.EVENT_TYPE_SAVE_FILE, "Saved changes to file")
}
