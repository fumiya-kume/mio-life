package nagoya.kuu.miolife.iijmio.model.volumelog

data class PacketLogModel(
    val date: String,
    val withCoupon: Int,
    val withoutCoupon: Int
)

