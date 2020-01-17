package nagoya.kuu.miolife.ui.main.sim

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

internal class SimDetailDialogViewModel(
    hdoServiceCode: String,
    useVolumeLogListLiveDataFactory: UseVolumeLogListLiveDataFactory
) : ViewModel() {

    private val useVolumeLogListLiveData =
        useVolumeLogListLiveDataFactory.create(hdoServiceCode, viewModelScope)
    val useVolumeLogList: LiveData<UseVolumeLogStatus> = useVolumeLogListLiveData
}