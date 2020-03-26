package nagoya.kuu.miolife.predict_estimate_volume_days.domain

interface PredictEstimateVolumeDaysUsecase {
    suspend fun execute(contractId: String): PredictEstimateVolumeDaysUsecaseResponse
}