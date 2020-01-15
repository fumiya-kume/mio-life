package nagoya.kuu.miolife.iijmio.remote.response

import nagoya.kuu.miolife.iijmio.entity.*

private fun CouponResponse.convert(): CouponModel {
    return CouponModel(
        this.volume,
        this.expire,
        this.type
    )
}

private fun HdoInfoResponse.convert(): HdoInfoModel {
    return HdoInfoModel(
        this.couponUse,
        this.couponResponseList.map { it.convert() },
        this.hdoServiceCode,
        this.sms,
        this.number,
        this.regulation,
        this.iccid,
        this.voice
    )
}

fun CouponInfoResponse.convert(): ContractListModel {
    return this.contractListResponse.map {
        val hddServiceCode = it.hddServiceCode
        val couponList = it.couponResponseList.map { it.convert() }
        val hdoInfoList = it.hdoInfoResponseList.map { it.convert() }

        return@map ContractModel(
            hddServiceCode,
            couponList,
            hdoInfoList,
            it.planName
        )
    }.convert()
}