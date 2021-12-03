package team.starfish.hellgate.poc.service

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import team.starfish.hellgate.poc.model.domain.PspRequest
import team.starfish.hellgate.poc.model.entity.PspRequestEntity

interface RequestService {
    fun addRequestRecord(request: PspRequest)
}

class CaptureRequestService(private val db: Database) : RequestService {
    override fun addRequestRecord(request: PspRequest) {
        transaction(db) {
            PspRequestEntity.new {
                this.configurationId = request.configurationId
                this.authorizationReference = request.authorizationReference
                this.createdAt = request.createdAt
            }
        }
    }
}