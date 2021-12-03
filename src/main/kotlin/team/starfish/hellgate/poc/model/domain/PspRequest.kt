package team.starfish.hellgate.poc.model.domain

import org.joda.time.DateTime
import java.util.UUID

data class PspRequest(
    val configurationId: UUID,
    val authorizationReference: UUID,
    val createdAt: DateTime
)

