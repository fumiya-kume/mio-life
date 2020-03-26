package nagoya.kuu.miolife.iijmio.remote.packetlog.domain

interface LoadPacketlogUsecase {
    suspend fun execute(): LoadPacketLogUsecaseStatus
}

