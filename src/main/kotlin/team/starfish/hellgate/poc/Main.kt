package team.starfish.hellgate.poc

import org.http4k.server.Netty
import org.http4k.server.asServer
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import team.starfish.hellgate.poc.http.PspRequestHandler
import team.starfish.hellgate.poc.service.CaptureRequestService
import team.starfish.hellgate.poc.service.RequestService

fun main() {
    startKoin {
        printLogger()
        modules(services, database)
    }
    PspRequestHandler().asServer(Netty(8080)).start()
}

val database = module {
    single { setupDatabase() }
}

val services = module {
    single<RequestService> { CaptureRequestService(get()) }
}
