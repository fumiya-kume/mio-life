package nagoya.kuu.miolife.predict_estimate_volume_days.infra

import org.junit.Assert
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("日時関係のリポジトリクラスのテスト")
internal class DatetimeRepositoryImplTest {
    @ParameterizedTest(name = "今日は{0}日, 今月は{1}日ある, 残り日数は{2}")
    @CsvSource(
        value = [
            "0,30,30",
            "10,30,20",
            "30,30,0"
        ]
    )
    fun `月末までの日数を取得する`(nowDays: Int, remainDays: Int, expected: Int) {
        val dateTimeRepositoryImpl = DatetimeRepositoryImpl()
        dateTimeRepositoryImpl.nowDays = nowDays
        dateTimeRepositoryImpl.daysLengthOfTMonth = remainDays

        val result = dateTimeRepositoryImpl.countMonthlyRemainDays()

        Assert.assertEquals(expected, result)
    }
}