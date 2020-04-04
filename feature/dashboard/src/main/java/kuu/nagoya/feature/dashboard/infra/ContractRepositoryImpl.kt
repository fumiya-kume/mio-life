package kuu.nagoya.feature.dashboard.infra

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kuu.nagoya.core.config.RepositoryResponse
import kuu.nagoya.data.api.IijMioAPiClient
import kuu.nagoya.data.api.response.CouponInfo
import kuu.nagoya.data.api.response.CouponInformationResponse
import kuu.nagoya.feature.dashboard.domain.ContractRepository
import kuu.nagoya.feature.dashboard.domain.entity.ContractEntity

internal class ContractRepositoryImpl(
    private val iijMioAPiClient: IijMioAPiClient
) : ContractRepository {

    private fun CouponInformationResponse.toContractEntity(): ContractEntity {
        val couponInfo = this.couponInfo.first()

        fun CouponInfo.toRemainVolume(): Int {
            return this.hdoInfo.sumBy { it.coupon.sumBy { it.volume } } +
                    this.hduInfo.sumBy { it.coupon.sumBy { it.volume } } +
                    this.hdxInfo.sumBy { it.coupon.sumBy { it.volume } }
        }

        return ContractEntity(
            couponInfo.hddServiceCode,
            couponInfo.plan,
            couponInfo.toRemainVolume()
        )
    }

    override suspend fun loadContractInformation(): Flow<RepositoryResponse<ContractEntity, Throwable>> =
        flow {
            this.emit(RepositoryResponse.Loading)

            runCatching {
                iijMioAPiClient.loadCouponInfo()
            }.fold(
                onSuccess = {
                    this.emit(RepositoryResponse.Success(it.toContractEntity()))
                },
                onFailure = {
                    this.emit(RepositoryResponse.Error(it))
                }
            )
        }
}
