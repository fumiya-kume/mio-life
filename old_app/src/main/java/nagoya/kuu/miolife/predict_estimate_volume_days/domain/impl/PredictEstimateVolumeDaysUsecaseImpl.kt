package nagoya.kuu.miolife.predict_estimate_volume_days.domain.impl

import nagoya.kuu.miolife.predict_estimate_volume_days.domain.PredictEstimateVolumeDaysUsecase
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.PredictEstimateVolumeDaysUsecaseError
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.PredictEstimateVolumeDaysUsecaseResponse
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.ContractReadonlyRepository
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.DatetimeRepository
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.SimVolumeReadonlyRepository

internal class PredictEstimateVolumeDaysUsecaseImpl(
    private val contractReadonlyRepository: ContractReadonlyRepository,
    private val simVolumeReadonlyRepository: SimVolumeReadonlyRepository,
    private val datetimeRepository: DatetimeRepository
) : PredictEstimateVolumeDaysUsecase {
    override suspend fun execute(contractId: String): PredictEstimateVolumeDaysUsecaseResponse {
        if (contractId.isEmpty()) {
            return PredictEstimateVolumeDaysUsecaseResponse.error(
                PredictEstimateVolumeDaysUsecaseError.CONTRACT_ID_IS_EMPTY
            )
        }
        val hasContractId = kotlin.runCatching {
            contractReadonlyRepository.hasContractId(contractId)
        }.onFailure {
            return PredictEstimateVolumeDaysUsecaseResponse.error(
                PredictEstimateVolumeDaysUsecaseError.CONTRACT_ID_LOADING_FAILED
            )
        }.getOrDefault(false)

        if (!hasContractId) {
            return PredictEstimateVolumeDaysUsecaseResponse.error(
                PredictEstimateVolumeDaysUsecaseError.CONTRACT_ID_NOT_FOUND
            )
        }

        val remainSimVolume =
            when (val response =
                simVolumeReadonlyRepository.loadContractRemainSimVolume(contractId)) {
                is SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.Success -> response.simVolume
                is SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.Error -> {
                    return PredictEstimateVolumeDaysUsecaseResponse.error(
                        PredictEstimateVolumeDaysUsecaseError.CONTRACT_ID_LOADING_FAILED
                    )
                }
            }

        val hasEnoughVolume = remainSimVolume >=
                (simVolumeReadonlyRepository.loadContractSimUsageAverage(contractId)
                        * datetimeRepository.countMonthlyRemainDays())

        if (hasEnoughVolume) {
            return PredictEstimateVolumeDaysUsecaseResponse.success
        } else {
            val average = simVolumeReadonlyRepository.loadContractSimUsageAverage(contractId)
            val remainDays = remainSimVolume / average

            return PredictEstimateVolumeDaysUsecaseResponse.Need(
                remainDays
            )
        }
    }
}