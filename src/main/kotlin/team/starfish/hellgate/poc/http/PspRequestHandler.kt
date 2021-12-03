package team.starfish.hellgate.poc.http

import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.format.Jackson.auto
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.joda.time.DateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import team.starfish.hellgate.poc.model.dto.CaptureRequestDTO
import team.starfish.hellgate.poc.model.domain.PspRequest
import team.starfish.hellgate.poc.service.RequestService

object PspRequestHandler : KoinComponent {

    private val requestService by inject<RequestService>()

    operator fun invoke(): HttpHandler = PrintRequest()
        .then(routes(makeCaptureRequest(), sayHell()))

    private fun sayHell(): RoutingHttpHandler {
        return "/" bind Method.GET to {
            Response(Status.OK).body("hello hell!")
        }
    }

    private fun makeCaptureRequest(): RoutingHttpHandler {
        val reqLens = Body.auto<CaptureRequestDTO>().toLens()
        return "/capture" bind Method.POST to { request: Request ->
            val (configurationId, authorizationReference) = reqLens(request)
            val pspReq = PspRequest(configurationId, authorizationReference, DateTime.now())
            //
            // #1 send capture request to Adyen here, pass amount and currency etc.
            //
            requestService.addRequestRecord(pspReq) // #2 then add record to local DB
            Response(Status.OK)
        }
    }
}


