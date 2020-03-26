package nagoya.kuu.miolife.ui.main.sim

import nagoya.kuu.miolife.ui.main.sim.viewentity.UseVolumeLogViewEntity

internal sealed class UseVolumeLogStatus {
    object Loading : UseVolumeLogStatus()
    data class Success(val useVolumeLogList: List<UseVolumeLogViewEntity>) : UseVolumeLogStatus()
    data class Failed(val errorMessage: String) : UseVolumeLogStatus()
}