package kuu.nagoya.data.api

import kuu.nagoya.core.config.AccessTokenObject
import kuu.nagoya.data.api.response.CouponInformationResponse
import kuu.nagoya.resources.AppConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class IijMioAPiClientImpl(
    private val appConfig: AppConfig
) : IijMioAPiClient {
    private val client: IijmioApi

    init {
        val accessToken = AccessTokenObject.accessToken
        val developerId = appConfig.iijmioDeveloperId

        val okHttpClient =
            OkHttpClient()
                .newBuilder()
                .addInterceptor {
                    it.proceed(
                        it.request()
                            .newBuilder()
                            .header("X-IIJmio-Developer", developerId)
                            .header("X-IIJmio-Authorization", accessToken)
                            .build()
                    )
                }
                .build()

        client = Retrofit
            .Builder()
            .baseUrl("https://api.iijmio.jp/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IijmioApi::class.java)
    }

    override suspend fun loadCouponInfo(): CouponInformationResponse = client.getCouponInformation()
}