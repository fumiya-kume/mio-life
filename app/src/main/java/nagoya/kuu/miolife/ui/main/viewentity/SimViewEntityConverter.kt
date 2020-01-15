package nagoya.kuu.miolife.ui.main.viewentity

import nagoya.kuu.miolife.iijmio.CouponRemainStatus
import nagoya.kuu.miolife.iijmio.local.HdoInfo

private fun String.toPhoneNumber(): String {
    return "${this.take(3)}-${this.drop(3).take(4)}-${this.drop(7).take(4)}"
}

private fun Int.toPhoneNumber(): String {
    return this.toString().toPhoneNumber()
}

fun CouponRemainStatus.HdoInfoResponse.converter(): SimViewEntity {

    val simTypeString =
        if (voice) {
            "音声"
        } else if (sms) {
            "SMS"
        } else {
            "データ"
        }

    return SimViewEntity(
        this.number.toPhoneNumber(),
        simTypeString
    )
}

fun HdoInfo.converter(): SimViewEntity {
    val simTypeString =
        if (voice) {
            "音声"
        } else if (sms) {
            "SMS"
        } else {
            "データ"
        }

    return SimViewEntity(
        this.number.toPhoneNumber(),
        simTypeString
    )
}