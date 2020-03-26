package nagoya.kuu.miolife.ui.main.sim

sealed class CouponSwitchStatus {
    object Loading : CouponSwitchStatus()
    data class Success(val checked: Boolean) : CouponSwitchStatus()
    data class Failed(val errorMessage: String) : CouponSwitchStatus()
}