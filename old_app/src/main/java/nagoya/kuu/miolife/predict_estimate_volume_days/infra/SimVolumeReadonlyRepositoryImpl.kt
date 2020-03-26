package nagoya.kuu.miolife.predict_estimate_volume_days.infra

import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.SimVolumeReadonlyRepository
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.LoadContractRemainSimVolumeResponseErrorKind

class SimVolumeReadonlyRepositoryImpl(
    private val apiLocalService: APILocalService
) : SimVolumeReadonlyRepository {
    override fun loadContractRemainSimVolume(contractId: String): LoadContractRemainSimVolumeResponse {
        val couponList =
            kotlin.runCatching {
                apiLocalService
                    .getAllCouponRemainList()
                    .contractList
                    .filter { it.hddServoceCode == contractId }
            }
                .fold(
                    onSuccess = { it },
                    onFailure = {
                        return LoadContractRemainSimVolumeResponse.Error(
                            LoadContractRemainSimVolumeResponseErrorKind.CANNOT_LOAD_SIM_INFO
                        )
                    }
                )

        if (couponList.isEmpty()) {
            return LoadContractRemainSimVolumeResponse.Error(
                LoadContractRemainSimVolumeResponseErrorKind.NOT_FOUND_IN_SIM_INFO
            )
        }

        return LoadContractRemainSimVolumeResponse.Success(0)
    }

    override fun loadContractSimUsageAverage(contractId: String): Int {
//        val contractModel = ContractModel(contractId)
//
//        val remainVolumeList = kotlin.runCatching {
//            apiLocalService.getCouponList(contractModel)
//
//        }

        TODO()
    }
}