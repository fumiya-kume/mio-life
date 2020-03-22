package nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository

interface DatetimeRepository {
    fun countMonthlyRemainDays(): Int
}