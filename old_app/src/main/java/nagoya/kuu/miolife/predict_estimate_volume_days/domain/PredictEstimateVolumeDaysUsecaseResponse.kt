package nagoya.kuu.miolife.predict_estimate_volume_days.domain

sealed class PredictEstimateVolumeDaysUsecaseResponse {
    data class error(val errorKind: PredictEstimateVolumeDaysUsecaseError) :
        PredictEstimateVolumeDaysUsecaseResponse()

    object success : PredictEstimateVolumeDaysUsecaseResponse()

    data class Need(val remainDays: Int) : PredictEstimateVolumeDaysUsecaseResponse()

}

sealed class PredictEstimateVolumeDaysUsecaseError {
    object CONTRACT_ID_IS_EMPTY : PredictEstimateVolumeDaysUsecaseError()
    object CONTRACT_ID_NOT_FOUND : PredictEstimateVolumeDaysUsecaseError()
    object CONTRACT_ID_LOADING_FAILED : PredictEstimateVolumeDaysUsecaseError()
}