package nagoya.kuu.miolife.iijmio.local

import nagoya.kuu.miolife.iijmio.entity.ContractListModel
import nagoya.kuu.miolife.iijmio.entity.ContractModel
import nagoya.kuu.miolife.iijmio.entity.CouponModel

interface APILocalService {
    fun addCouponRemainEntitiy(contractModel: ContractModel)
    fun getCouponList(contractModel: ContractModel): List<CouponModel>
    fun getAllCouponRemainList(): ContractListModel
    fun insertOrUpdateCouponRemainEntity(contractModel: ContractModel)
}