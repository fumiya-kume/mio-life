package nagoya.kuu.miolife.iijmio.local

import nagoya.kuu.miolife.iijmio.entity.ContractModel
import nagoya.kuu.miolife.iijmio.entity.HdoInfoModel


fun CouponAndHdo.convertBack(): ContractModel {
    val couponList = this.couponList

    fun HdoInfo.convert(): HdoInfoModel {
        return HdoInfoModel(
            this.couponUse,
            couponList.map { it.convertBack() },
            this.hdoServiceCode,
            this.sms,
            this.number.toString(),
            this.regulation,
            this.iccId,
            this.voice
        )
    }

    return ContractModel(
        this.couponRemainEntity.hddServiceCode,
        this.couponList.map { it.convertBack() },
        this.hdoInfoList.map { it.convert() },
        couponRemainEntity.planName
    )
}