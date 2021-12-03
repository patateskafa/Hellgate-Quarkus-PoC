package team.starfish.hellgate.poc.model.dto

import java.util.UUID

interface PspRequestDTO

data class CaptureRequestDTO (
    val configurationId: UUID,
    val authorizationReference: UUID,
    val amount: Int,
    val currency: String
): PspRequestDTO