package nagoya.kuu.miolife.iijmio.remote.couponuse

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.put
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import nagoya.kuu.miolife.BuildConfig
import nagoya.kuu.miolife.credential.AccessTokenRepository
import nagoya.kuu.miolife.iijmio.remote.couponuse.request.CouponUseRequest
import nagoya.kuu.miolife.iijmio.remote.couponuse.response.CouponUseResponseStatus

class UpdateCouponseSwitchUsecaseImpl(
    private val accessTokenRepository: AccessTokenRepository
) : UpdateCouponseSwitchUsecase {

    private val httpClient = HttpClient(Android) {
        DefaultRequest {
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.unquoted)
        }
    }

    override suspend fun execute(couponUseRequest: CouponUseRequest): CouponUseResponseStatus {

        suspend fun <T> HttpResponse.parse(targetType: KSerializer<T>): T {
            return Json(JsonConfiguration.Stable).parse(targetType, this.readText())
        }

        try {
            val accesstoken = accessTokenRepository.loadAccessToken() ?: ""
            val result =
                httpClient.put<HttpResponse>(Url("https://api.iijmio.jp/mobile/d/v2/coupon/")) {
                    this.headers.append(
                        "X-IIJmio-Authorization",
                        accesstoken
                    )
                    this.headers.append("X-IIJmio-Developer", BuildConfig.DEVELOPER_ID)
                    this.headers.append("Content-Type", "application/json")
                    body = Json(JsonConfiguration.Default).stringify(
                        CouponUseRequest.serializer(),
                        couponUseRequest
                    )
                }

            return result.parse(CouponUseResponseStatus.Success.serializer())

        } catch (e: ClientRequestException) {
            return when (e.response.status) {
                HttpStatusCode(202, "") ->
                    return e.response.parse(CouponUseResponseStatus.Success.serializer())
                HttpStatusCode(
                    403,
                    "error"
                ) -> e.response.parse(CouponUseResponseStatus.Error.serializer())
                HttpStatusCode(
                    405,
                    "error"
                ) -> e.response.parse(CouponUseResponseStatus.Error.serializer())
                HttpStatusCode(
                    412,
                    "error"
                ) -> e.response.parse(CouponUseResponseStatus.Error.serializer())

                HttpStatusCode(
                    429,
                    ""
                ) -> e.response.parse(CouponUseResponseStatus.RequestLimited.serializer())

                HttpStatusCode(
                    500,
                    "error"
                ) -> e.response.parse(CouponUseResponseStatus.ServerError.serializer())
                HttpStatusCode(
                    503,
                    "error"
                ) -> e.response.parse(CouponUseResponseStatus.ServerMaintenance.serializer())

                else -> e.response.parse(CouponUseResponseStatus.Error.serializer())
            }
        }


    }
}