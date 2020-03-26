package nagoya.kuu.miolife.iijmio.local

import nagoya.kuu.miolife.iijmio.model.contract.HdoInfoModel

fun HdoInfoModel.convert(hddServiceCode: String): HdoInfo {
    return HdoInfo(
        0,
        this.hdoServiceCode,
        hddServiceCode,
        this.couponUse,
        this.couponResponseList.map { it.volume }.fold(0) { i, i2 -> i + i2 },
        this.sms,
        this.number,
        this.regulation,
        this.iccid,
        this.voice
    )
}