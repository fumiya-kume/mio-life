package kuu.nagoya.data.api.response

data class CouponInfo(
    val hddServiceCode: String = "",
    val plan: String = "",
    val hdoInfo: List<HdoInfo> = emptyList(),
    val hduInfo: List<HduInfo> = emptyList(),
    val hdxInfo: List<HdxInfo> = emptyList()
)
