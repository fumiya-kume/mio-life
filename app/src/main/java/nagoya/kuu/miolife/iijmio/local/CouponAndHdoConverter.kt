package nagoya.kuu.miolife.iijmio.local

import nagoya.kuu.miolife.iijmio.model.contract.ContractModel
import nagoya.kuu.miolife.iijmio.model.contract.HdoInfoModel
import nagoya.kuu.miolife.iijmio.model.contract.HduInfoModel
import nagoya.kuu.miolife.iijmio.model.contract.HdxInfoModel


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

    fun HduInfo.convert(): HduInfoModel {
        return HduInfoModel(
            couponUse = this.couponUse,
            couponResponseList = couponList.map { it.convertBack() },
            hduServiceCode = this.hduServiceCode,
            sms = this.sms,
            number = this.number.toString(),
            regulation = this.regulation,
            iccid = this.iccId,
            voice = this.voice
        )
    }

    fun HdxInfo.convert(): HdxInfoModel {
        return HdxInfoModel(
            couponUse = this.couponUse,
            couponResponseList = couponList.map { it.convertBack() },
            hdxServiceCode = this.hdxServiceCode,
            sms = this.sms,
            number = this.number.toString(),
            regulation = this.regulation,
            iccid = this.iccId,
            voice = this.voice
        )
    }



    return ContractModel(
        hddServoceCode = this.couponRemainEntity.hddServiceCode,
        couponList = this.couponList.map { it.convertBack() },
        hdoInfoList = this.hdoInfoList.map { it.convert() },
        hduInfoList = this.hduInfoList.map { it.convert() },
        hdxInfoList = this.hdxInfoList.map { it.convert() },
        planName = couponRemainEntity.planName
    )
}