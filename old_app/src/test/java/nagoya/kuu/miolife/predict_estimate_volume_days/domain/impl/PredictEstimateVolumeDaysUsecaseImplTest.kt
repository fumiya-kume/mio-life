package nagoya.kuu.miolife.predict_estimate_volume_days.domain.impl

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.PredictEstimateVolumeDaysUsecase
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.PredictEstimateVolumeDaysUsecaseError
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.PredictEstimateVolumeDaysUsecaseResponse
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.ContractReadonlyRepository
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.DatetimeRepository
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.SimVolumeReadonlyRepository
import org.junit.Assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class PredictEstimateVolumeDaysUsecaseImplTest : Spek({

    describe("あと何日ギガが持つか計算するUsecase") {

        val contractReadonlyRepository: ContractReadonlyRepository = mock()
        val simVolumeReadonlyRepository: SimVolumeReadonlyRepository = mock()
        val datetimeRepository: DatetimeRepository = mock()

        val predictEstimateVolumeUsecase: PredictEstimateVolumeDaysUsecase =
            PredictEstimateVolumeDaysUsecaseImpl(
                contractReadonlyRepository,
                simVolumeReadonlyRepository,
                datetimeRepository
            )

        beforeEach {
            reset(
                contractReadonlyRepository,
                simVolumeReadonlyRepository,
                datetimeRepository
            )
        }

        describe("無効な契約IDを渡すと無効") {
            describe("空文字の契約IDの場合") {
                runBlocking {
                    val contractId = ""
                    val actual = predictEstimateVolumeUsecase.execute(contractId)

                    it("エラーである") {
                        Assert.assertTrue((actual is PredictEstimateVolumeDaysUsecaseResponse.error))
                    }
                    it("契約IDが空文字である") {
                        Assert.assertEquals(
                            (actual as PredictEstimateVolumeDaysUsecaseResponse.error).errorKind,
                            PredictEstimateVolumeDaysUsecaseError.CONTRACT_ID_IS_EMPTY
                        )
                    }
                }
            }

            context("存在しない契約IDの場合") {
                runBlocking {
                    val contractId = "hogehoge"

                    whenever(contractReadonlyRepository.hasContractId(contractId)).thenReturn(false)

                    val actual = predictEstimateVolumeUsecase.execute(contractId)

                    it("エラーである") {
                        Assert.assertTrue(
                            actual is PredictEstimateVolumeDaysUsecaseResponse.error
                        )
                    }

                    it("契約IDが存在しないエラーが返される") {
                        Assert.assertEquals(
                            (actual as PredictEstimateVolumeDaysUsecaseResponse.error).errorKind,
                            PredictEstimateVolumeDaysUsecaseError.CONTRACT_ID_NOT_FOUND
                        )
                    }
                }
            }

            context("契約者情報についての読み込みに失敗した時") {
                val contractId = "hogehoge"

                whenever(contractReadonlyRepository.hasContractId(contractId)).thenAnswer { Exception() }

                runBlocking {
                    val actual = predictEstimateVolumeUsecase.execute(contractId)

                    it("エラーである") {
                        Assert.assertTrue(actual is PredictEstimateVolumeDaysUsecaseResponse.error)
                    }

                    it("読み込みに失敗である") {
                        Assert.assertEquals(
                            (actual as PredictEstimateVolumeDaysUsecaseResponse.error).errorKind,
                            PredictEstimateVolumeDaysUsecaseError.CONTRACT_ID_LOADING_FAILED
                        )
                    }
                }
            }
        }

        context("月末までクーポンが持つ場合持つというステータスを返す") {
            val dummyContractId = "hoge"

            whenever(contractReadonlyRepository.hasContractId(dummyContractId)).thenReturn(true)

            class argument(
                val remain: Int,
                val remainDays: Int,
                val use: Int
            )

            listOf(
                argument(1, 0, 1),
                argument(10, 0, 1),
                argument(100, 10, 10),
                argument(10000, 100, 100)
            ).forEach {
                runBlocking {
                    whenever(simVolumeReadonlyRepository.loadContractRemainSimVolume(dummyContractId)).thenReturn(
                        SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.Success(it.remain)
                    )
                    whenever(simVolumeReadonlyRepository.loadContractSimUsageAverage(dummyContractId)).thenReturn(
                        it.use
                    )
                    whenever(datetimeRepository.countMonthlyRemainDays()).thenReturn(
                        it.remainDays
                    )

                    val actual = predictEstimateVolumeUsecase.execute(dummyContractId)

                    it("残量が${it.remain}md,月末まで${it.remainDays}日,毎日平均で${it.use}md使用") {
                        Assert.assertEquals(
                            actual,
                            PredictEstimateVolumeDaysUsecaseResponse.success
                        )
                    }
                }
            }
        }

        context("月末までクーポンが持たない場合持つ日数を返す") {
            class argument(
                val volume: Int,
                val days: Int,
                val average: Int,
                val expectedDays: Int
            )

            listOf(
                argument(10, 2, 10, 1),
                argument(100, 10, 50, 2),
                argument(15000, 20, 1000, 15),
                argument(15500, 20, 1000, 15)
            ).forEach {
                val contractId = "hoge"

                runBlocking {
                    whenever(simVolumeReadonlyRepository.loadContractRemainSimVolume(contractId = contractId)).thenReturn(
                        SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.Success(it.volume)
                    )
                    whenever(simVolumeReadonlyRepository.loadContractSimUsageAverage(contractId)).thenReturn(
                        it.average
                    )
                    whenever(datetimeRepository.countMonthlyRemainDays()).thenReturn(it.days)

                    val actual = predictEstimateVolumeUsecase.execute(contractId)

                    it("残量が${it.volume}md,月末まで${it.days}日,毎日平均で${it.average}md利用=>${it.expectedDays}日持つ") {
                        Assert.assertTrue(
                            actual is PredictEstimateVolumeDaysUsecaseResponse.Need
                        )
                        Assert.assertEquals(
                            (actual as PredictEstimateVolumeDaysUsecaseResponse.Need).remainDays,
                            it.expectedDays
                        )
                    }
                }
            }
        }
    }
})