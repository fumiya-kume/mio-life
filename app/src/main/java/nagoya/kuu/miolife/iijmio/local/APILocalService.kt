package nagoya.kuu.miolife.iijmio.local

import nagoya.kuu.miolife.iijmio.model.contract.ContractListModel
import nagoya.kuu.miolife.iijmio.model.contract.ContractModel
import nagoya.kuu.miolife.iijmio.model.contract.CouponModel

interface APILocalService {
    fun addCouponRemainEntitiy(contractModel: ContractModel)
    fun getCouponList(contractModel: ContractModel): List<CouponModel>
    fun getAllCouponRemainList(): ContractListModel
    fun insertOrUpdateCouponRemainEntity(contractModel: ContractModel)
}