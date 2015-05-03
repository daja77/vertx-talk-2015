package linuxwochen

def server = vertx.createHttpServer()

def config = ["prefix": "/eventbus"]
def eb = vertx.eventBus

container.deployVerticle("DateSender.groovy")

eb.registerHandler("datetimeupdate", {
    message -> println("${message.body}")
} )

eb.registerHandler("browser", {
    message -> println("browser connected:" + new Date().toString() + ": ${message.body}")

})

server.requestHandler { req  ->
    def file = req.path == "/" ? "sockjsclient.html" : req.path
    //attention, might be escaped ..
    req.response.sendFile("./" + file)
}

vertx.createSockJSServer(server).bridge(config, [[:]], [[:]])

server.listen(8080)
