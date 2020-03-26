package nagoya.kuu.miolife.ui.main.sim

import kotlinx.coroutines.CoroutineScope
import nagoya.kuu.miolife.iijmio.remote.packetlog.domain.LoadPacketlogUsecase

internal class UseVolumeLogListLiveDataFactory(
    private val loadPacketlogUsecase: LoadPacketlogUsecase
) {
    fun create(
        hdoServiceCode: String,
        coroutineScope: CoroutineScope
    ): UseVolumeLogListLiveData {
        return UseVolumeLogListLiveData(
            hdoServiceCode = hdoServiceCode,
            loadPacketlogUsecase = loadPacketlogUsecase,
            coroutineScope = coroutineScope
        )
    }
}