package nagoya.kuu.miolife.predict_estimate_volume_days.infra

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import nagoya.kuu.miolife.iijmio.local.APILocalService
import nagoya.kuu.miolife.iijmio.model.contract.ContractListModel
import nagoya.kuu.miolife.iijmio.model.contract.ContractModel
import nagoya.kuu.miolife.predict_estimate_volume_days.domain.repository.SimVolumeReadonlyRepository
import org.junit.Assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

internal class SimVolumeReadonlyRepositoryImplTest : Spek({

    // mock
    val apiLocalService = mock<APILocalService>()

    // static value
    val dummyContractId = "hoge"

    // test target
    val simVolumeReadonlyRepository =
        SimVolumeReadonlyRepositoryImpl(
            apiLocalService
        )

    describe("Simに残っているクーポン残量を取得できる") {

        beforeEachTest {
            reset(apiLocalService)
        }

        context("利用したSimの情報を取得する時に例外が発生する場合") {

            whenever(apiLocalService.getAllCouponRemainList()).thenAnswer {
                return@thenAnswer Exception()
            }

            val actual =
                simVolumeReadonlyRepository.loadContractRemainSimVolume(dummyContractId)

            it("エラーである旨が返ってくる") {
                Assert.assertEquals(
                    actual::class,
                    SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.Error::class
                )
            }

            it("エラー内容は読み込めない旨である") {
                Assert.assertEquals(
                    (actual as SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.Error).kindLoadContractRemain,
                    SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.LoadContractRemainSimVolumeResponseErrorKind.CANNOT_LOAD_SIM_INFO
                )
            }
        }

        context("契約者IDを含んだSimの情報を取得できない時") {

            listOf(
                emptyList(),
                listOf(
                    ContractModel(hddServoceCode = "hdd")
                )
            ).map {
                ContractListModel(it)
            }.forEach { contractListModel ->

                reset(apiLocalService)

                whenever(apiLocalService.getAllCouponRemainList()).thenReturn(
                    contractListModel
                )

                val actual =
                    simVolumeReadonlyRepository.loadContractRemainSimVolume(dummyContractId)

                it(description = contractListModel.contractList.map { it.hddServoceCode }
                    .toString()) {
                    Assert.assertEquals(
                        actual::class,
                        SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.Error::class
                    )

                    Assert.assertEquals(
                        (actual as SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.Error).kindLoadContractRemain,
                        SimVolumeReadonlyRepository.LoadContractRemainSimVolumeResponse.LoadContractRemainSimVolumeResponseErrorKind.NOT_FOUND_IN_SIM_INFO
                    )
                }
            }
        }
    }
})
