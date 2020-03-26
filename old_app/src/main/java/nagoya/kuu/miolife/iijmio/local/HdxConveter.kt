package nagoya.kuu.miolife.iijmio.local

import nagoya.kuu.miolife.iijmio.model.contract.HdxInfoModel

fun HdxInfoModel.convert(hddServiceCode: String): HdxInfo {
    return HdxInfo(
        id = 0,
        hdxServiceCode = this.hdxServiceCode,
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