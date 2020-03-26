package nagoya.kuu.miolife.iijmio.remote.packetlog.domain

import kotlinx.serialization.Serializable
import nagoya.kuu.miolife.iijmio.model.volumelog.PacketLogRootModel

sealed class LoadPacketLogUsecaseStatus {
    data class Success(
        val packetLogRootModel: PacketLogRootModel
    ) : LoadPacketLogUsecaseStatus()

    @Serializable
    data class Error(
        val returnCode: String
    ) : LoadPacketLogUsecaseStatus()

    @Serializable
    data class RequestLimited(
        val returnCode: String
    ) : LoadPacketLogUsecaseStatus()

    object ServerError : LoadPacketLogUsecaseStatus()
    object ServerMaintenance : LoadPacketLogUsecaseStatus()

}