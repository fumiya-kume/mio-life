package nagoya.kuu.miolife.ui.main.viewentity

import nagoya.kuu.miolife.iijmio.CouponRemainStatus
import nagoya.kuu.miolife.iijmio.local.HdoInfo

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
        this.number.toString(),
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
        this.number.toString(),
        simTypeString
    )
}