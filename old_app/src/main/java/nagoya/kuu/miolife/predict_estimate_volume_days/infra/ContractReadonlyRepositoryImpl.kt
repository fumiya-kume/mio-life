package nagoya.kuu.miolife.predict_estimate_volume_days.infra

import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.iijmio.model.contract.ContractListModel
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.ContractReadonlyRepository

internal class ContractReadonlyRepositoryImpl(
    private val apiLocalService: APILocalService
) : ContractReadonlyRepository {
    override fun hasContractId(contractId: String): Boolean {
        val contractList = kotlin.runCatching {
            apiLocalService.getAllCouponRemainList()
        }.getOrNull() ?: ContractListModel(emptyList())

        return contractList.contractList.map { it.hddServoceCode }.contains(contractId)
    }
}