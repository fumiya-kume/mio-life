package nagoya.kuu.miolife.ui.main.viewentity

import nagoya.kuu.miolife.iijmio.model.contract.HdoInfoModel

private fun String.toPhoneNumber(): String {
    return "${this.take(3)}-${this.drop(3).take(4)}-${this.drop(7).take(4)}"
}

private fun generateSimTypeString(voice: Boolean, sms: Boolean): String {
    return if (voice) {
        "音声"
    } else if (sms) {
        "SMS"
    } else {
        "データ"
    }
}

fun HdoInfoModel.convert(): SimViewEntity {
    return SimViewEntity(
        phoneNumber = this.number.toPhoneNumber(),
        simType = generateSimTypeString(
            voice = this.voice,
            sms = this.sms
        ),
        hdoServiceCode = this.hdoServiceCode
    )
}
