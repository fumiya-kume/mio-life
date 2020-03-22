package nagoya.kuu.miolife.predict_estimate_volume_days.infra

import androidx.annotation.VisibleForTesting
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.DatetimeRepository
import org.threeten.bp.LocalDate

class DatetimeRepositoryImpl :
    DatetimeRepository {

    @VisibleForTesting
    var nowDays = org.threeten.bp.LocalDate.now().dayOfMonth

    @VisibleForTesting
    var daysLengthOfTMonth = LocalDate.now().month.length(LocalDate.now().isLeapYear)

    override fun countMonthlyRemainDays(): Int {
        return daysLengthOfTMonth - nowDays
    }
}