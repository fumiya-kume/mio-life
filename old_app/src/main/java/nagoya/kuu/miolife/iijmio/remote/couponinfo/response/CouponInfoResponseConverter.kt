package nagoya.kuu.miolife.iijmio.remote.couponinfo.response

import nagoya.kuu.miolife.iijmio.model.contract.*

private fun CouponResponse.convert(): CouponModel {
    return CouponModel(
        volume = this.volume,
        expire = this.expire,
        type = this.type
    )
}

private fun HdoInfoResponse.convert(): HdoInfoModel {
    return HdoInfoModel(
        couponUse = this.couponUse,
        couponResponseList = this.couponResponseList.map { it.convert() },
        hdoServiceCode = this.hdoServiceCode,
        sms = this.sms,
        number = this.number,
        regulation = this.regulation,
        iccid = this.iccid,
        voice = this.voice
    )
}

private fun HduInfoResponse.convert(): HduInfoModel {
    return HduInfoModel(
        couponUse = this.couponUse,
        couponResponseList = this.couponResponseList.map { it.convert() },
        hduServiceCode = this.hduServiceCode,
        sms = this.sms,
        number = this.number,
        regulation = this.regulation,
        iccid = this.iccid,
        voice = this.voice
    )
}

private fun HdxInfoResponse.convert(): HdxInfoModel {
    return HdxInfoModel(
        couponUse = this.couponUse,
        couponResponseList = this.couponResponseList.map { it.convert() },
        hdxServiceCode = this.hdxServiceCode,
        sms = this.sms,
        number = this.number,
        regulation = this.regulation,
        iccid = this.iccid,
        voice = this.voice
    )
}

fun CouponInfoResponse.convert(): ContractListModel {
    return this.contractListResponse.map {
        val hddServiceCode = it.hddServiceCode
        val couponList = it.couponResponseList.map { it.convert() }
        val hdoInfoList = it.hdoInfoResponseList.map { it.convert() }
        val hduInfoList = it.hduInfoResponseList.map { it.convert() }
        val hdxInfoList = it.hdxInfoResponseList.map { it.convert() }


        return@map ContractModel(
            hddServoceCode = hddServiceCode,
            couponList = couponList,
            hdoInfoList = hdoInfoList,
            hduInfoList = hduInfoList,
            hdxInfoList = hdxInfoList,
            planName = it.planName
        )
    }.convert()
}