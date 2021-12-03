package team.starfish.hellgate.poc.model.entity;

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.jodatime.datetime
import java.util.UUID

object PspRequests : UUIDTable() {
    val configurationId = uuid("configuration_id")
    val authorizationReference = uuid("authorization_reference")
    val createdAt = datetime("created_at")
}

class PspRequestEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<PspRequestEntity>(PspRequests)

    var configurationId by PspRequests.configurationId
    var authorizationReference by PspRequests.authorizationReference
    var createdAt by PspRequests.createdAt

    override fun toString() = "PspRequest($configurationId, $authorizationReference, $createdAt)"
}