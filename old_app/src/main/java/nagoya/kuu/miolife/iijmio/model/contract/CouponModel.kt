package nagoya.kuu.miolife.iijmio.model.contract

data class CouponModel(
    val volume: Int = 0,
    val expire: String? = "",
    val type: String = ""
)