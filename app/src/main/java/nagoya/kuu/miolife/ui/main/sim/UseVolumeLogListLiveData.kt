package nagoya.kuu.miolife.ui.main.sim

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nagoya.kuu.miolife.iijmio.remote.packetlog.domain.LoadPacketLogUsecaseStatus
import nagoya.kuu.miolife.iijmio.remote.packetlog.domain.LoadPacketlogUsecase
import nagoya.kuu.miolife.ui.main.sim.viewentity.convert

internal class UseVolumeLogListLiveData(
    private val hdoServiceCode: String,
    private val loadPacketlogUsecase: LoadPacketlogUsecase,
    coroutineScope: CoroutineScope
) : LiveData<UseVolumeLogStatus>() {
    init {
        postValue(
            UseVolumeLogStatus.Loading
        )

        coroutineScope.launch(Dispatchers.IO) {
            when (val response = loadPacketlogUsecase.execute()) {
                is LoadPacketLogUsecaseStatus.Success -> {
                    postValue(
                        UseVolumeLogStatus.Success(
                            response.packetLogRootModel.convert(hdoServiceCode)
                        )
                    )
                }
                is LoadPacketLogUsecaseStatus.Error -> {
                    postValue(
                        UseVolumeLogStatus.Failed(response.returnCode)
                    )
                }
                is LoadPacketLogUsecaseStatus.RequestLimited -> {
                    postValue(
                        UseVolumeLogStatus.Failed("リクエストが多すぎます")
                    )
                }
                LoadPacketLogUsecaseStatus.ServerError -> {
                    postValue(
                        UseVolumeLogStatus.Failed("サーバーエラー")
                    )
                }
                LoadPacketLogUsecaseStatus.ServerMaintenance -> {
                    postValue(
                        UseVolumeLogStatus.Failed("サーバーメンテナンス中")
                    )
                }
            }
        }
    }
}