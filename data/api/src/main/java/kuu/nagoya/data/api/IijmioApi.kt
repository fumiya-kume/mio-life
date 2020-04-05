package kuu.nagoya.data.api

import kuu.nagoya.data.api.response.CouponInformationResponse
import retrofit2.http.GET

internal interface IijmioApi {
    @GET("mobile/d/v2/coupon/")
    suspend fun getCouponInformation(): CouponInformationResponse
}