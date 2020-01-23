package nagoya.kuu.miolife.ui.main.sim

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

internal class SimDetailDialogViewModel(
    hdoServiceCode: String,
    useVolumeLogListLiveDataFactory: UseVolumeLogListLiveDataFactory,
    private val couponSwitchLiveDataFactory: CouponSwitchLiveDataFactory
) : ViewModel() {

    private val useVolumeLogListLiveData =
        useVolumeLogListLiveDataFactory.create(hdoServiceCode, viewModelScope)
    val useVolumeLogList: LiveData<UseVolumeLogStatus> = useVolumeLogListLiveData

    private val couponSwitchLiveData =
        couponSwitchLiveDataFactory.create(hdoServiceCode, viewModelScope)
    val couponSwitch: LiveData<CouponSwitchStatus> = couponSwitchLiveData

    fun refreshSwitchData() {
        couponSwitchLiveData.refreshSwitchData()
    }

    fun updateCouponSwitch(newValue: Boolean, serviceCode: String) {
        couponSwitchLiveData.updateSwitchValue(newValue, serviceCode)
    }
}