package nagoya.kuu.miolife.iijmio.local

import nagoya.kuu.miolife.iijmio.model.contract.HduInfoModel

fun HduInfoModel.convert(hddServiceCode: String): HduInfo {
    return HduInfo(
        id = 0,
        hduServiceCode = this.hduServiceCode,
        hddServiceCode = hddServiceCode,
        couponUse = this.couponUse,
        simVolume = this.couponResponseList.map { it.volume }.fold(0) { i, i2 -> i + i2 },
        sms = this.sms,
        number = this.number,
        regulation = this.regulation,
        iccId = this.iccid,
        voice = this.voice
    )
}