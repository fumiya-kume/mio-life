package kuu.nagoya.feature.dashboard.infra

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kuu.nagoya.core.config.RepositoryResponse
import kuu.nagoya.data.api.IijMioAPiClient
import kuu.nagoya.data.api.response.CouponInfo
import kuu.nagoya.data.api.response.HdoInfo
import kuu.nagoya.data.api.response.HduInfo
import kuu.nagoya.data.api.response.HdxInfo
import kuu.nagoya.feature.dashboard.domain.SimInfoRepository
import kuu.nagoya.feature.dashboard.domain.entity.SimEntity

internal class SimInfoRepositoryImpl(
    private val iijMioAPiClient: IijMioAPiClient
) :
    SimInfoRepository {
    override suspend fun loadSimInfo(): Flow<RepositoryResponse<List<SimEntity>, Throwable>> =
        flow {
            this.emit(RepositoryResponse.Loading)

            fun HdoInfo.toSimEntity(id: Int = 0): SimEntity {
                return SimEntity(
                    id,
                    this.iccid,
                    this.number,
                    this.couponUse
                )
            }

            fun HduInfo.toSimEntity(id: Int = 0): SimEntity {
                return SimEntity(
                    id,
                    this.iccid,
                    this.number,
                    this.couponUse
                )
            }

            fun HdxInfo.toSimEntity(id: Int = 0): SimEntity {
                return SimEntity(
                    id,
                    this.iccid,
                    this.number,
                    this.couponUse
                )
            }

            fun CouponInfo.toSimListInfo(): List<SimEntity> {
                return listOf(
                    this.hdoInfo.mapIndexed { index, hdoInfo -> hdoInfo.toSimEntity(index) },
                    this.hduInfo.mapIndexed { index, hdoInfo -> hdoInfo.toSimEntity(index) },
                    this.hdxInfo.mapIndexed { index, hdoInfo -> hdoInfo.toSimEntity(index) }
                ).flatten()
            }

            runCatching {
                iijMioAPiClient.loadCouponInfo().couponInfo.first().toSimListInfo()
            }.fold(
                onSuccess = {
                    this.emit(RepositoryResponse.Success(it))
                },
                onFailure = {
                    this.emit(RepositoryResponse.Error(it))
                }
            )
        }
}
