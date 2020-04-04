package kuu.nagoya.data.api

import kuu.nagoya.data.api.response.CouponInformationResponse

interface IijMioAPiClient {
    suspend fun loadCouponInfo(): CouponInformationResponse
}