/**
 * Created by daja on 03.05.15.
 */

package linuxwochen

def eb = vertx.eventBus

def timerId = vertx.setPeriodic(1000) {
    timerId ->
        eb.publish("datetimeupdate", new Date().toString())
    }
