package nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository

interface SimVolumeReadonlyRepository {
    fun loadContractRemainSimVolume(contractId: String): LoadContractRemainSimVolumeResponse

    sealed class LoadContractRemainSimVolumeResponse {
        data class Success(val simVolume: Int) : LoadContractRemainSimVolumeResponse()
        data class Error(val kindLoadContractRemain: LoadContractRemainSimVolumeResponseErrorKind) :
            LoadContractRemainSimVolumeResponse()

        enum class LoadContractRemainSimVolumeResponseErrorKind {
            CANNOT_LOAD_SIM_INFO,
            NOT_FOUND_IN_SIM_INFO
        }
    }

    fun loadContractSimUsageAverage(contractId: String): Int
}