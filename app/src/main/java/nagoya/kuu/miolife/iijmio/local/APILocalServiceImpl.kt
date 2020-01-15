package nagoya.kuu.miolife.iijmio.local

import android.content.Context
import nagoya.kuu.miolife.iijmio.entity.ContractListModel
import nagoya.kuu.miolife.iijmio.entity.ContractModel
import nagoya.kuu.miolife.iijmio.entity.CouponModel
import nagoya.kuu.miolife.iijmio.entity.convert

class APILocalServiceImpl(
    private val context: Context
) : APILocalService {
    private val couponRemainDatabase: CouponRemainDatabase =
        CouponRemainDatabase.getDatabase(context)

    override fun addCouponRemainEntitiy(contractModel: ContractModel) {
        return couponRemainDatabase.couponRemainDao().insertCouponRemainDao(contractModel.convert())
    }

    private fun ContractModel.convert(): CouponRemainEntity {
        return CouponRemainEntity(
            this.hddServoceCode,
            this.planName
        )
    }

    override fun insertOrUpdateCouponRemainEntity(contractModel: ContractModel) {
        val couponRemainList = couponRemainDatabase.couponRemainDao()
            .getCouponRemainDao(contractModel.hddServoceCode)

        val hasCouponRemainList = couponRemainList.isNotEmpty()
        if (hasCouponRemainList) {
            couponRemainDatabase.couponRemainDao().updateCouponRemainDao(contractModel.convert())
        } else {
            couponRemainDatabase.couponRemainDao().insertCouponRemainDao(contractModel.convert())
        }

        contractModel.couponList.forEach {
            val couponDao = couponRemainDatabase.CouponDao()

            val couponList = couponDao.getCoupon(contractModel.hddServoceCode)
            if (couponList.isEmpty()) {
                couponDao.addCoupon(it.convert(contractModel.hddServoceCode))
            } else {
                couponDao.updateCoupon(it.convert(contractModel.hddServoceCode))
            }
        }

        contractModel.hdoInfoList.forEach {
            val hdoInfoModel = it
            val hdoInfoDao = couponRemainDatabase.HdoInfoDao()
            val hdoInfoList = hdoInfoDao.getHdoInfo(contractModel.hddServoceCode)
                .filter { it.number == hdoInfoModel.number }
            if (hdoInfoList.isEmpty()) {
                hdoInfoDao.addHdoInfo(it.convert(contractModel.hddServoceCode))
            } else {
                hdoInfoDao.updateHdoInfo(it.convert(contractModel.hddServoceCode))
            }
        }
    }

    override fun getCouponList(contractModel: ContractModel): List<CouponModel> {
        val hddServiceCode = contractModel.hddServoceCode
        return couponRemainDatabase.CouponDao().getCoupon(hddServiceCode).map { it.convertBack() }
    }

    override fun getAllCouponRemainList(): ContractListModel {
        return couponRemainDatabase
            .couponRemainDao()
            .getAllCouponRemainDao()
            .map { it.convertBack() }
            .convert()
    }
}