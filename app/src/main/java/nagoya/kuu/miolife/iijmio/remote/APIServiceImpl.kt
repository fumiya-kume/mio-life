package nagoya.kuu.miolife.iijmio.remote

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import nagoya.kuu.miolife.BuildConfig
import nagoya.kuu.miolife.iijmio.accesstoken.AccessTokenRepository
import nagoya.kuu.miolife.iijmio.remote.response.CouponInfoResponse
import nagoya.kuu.miolife.iijmio.remote.response.convert

class APIServiceImpl(
    private val context: Context,
    private val accessTokenRepository: AccessTokenRepository
) : APIService {
    private val httpClient = HttpClient(Android) {
        DefaultRequest {
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.unquoted)
        }
    }

    override suspend fun getCouponRemainData(): CouponRemainStatus {

        suspend fun <T> HttpResponse.parse(targetType: KSerializer<T>): T {
            return Json(JsonConfiguration.Stable).parse(targetType, this.readText())
        }

        try {
            val accesstoken = accessTokenRepository.loadAccessToken() ?: ""
            val result =
                httpClient.get<HttpResponse>(Url("https://api.iijmio.jp/mobile/d/v2/coupon/")) {
                    this.headers.append(
                        "X-IIJmio-Authorization",
                        accesstoken
                    )
                    this.headers.append("X-IIJmio-Developer", BuildConfig.DEVELOPER_ID)
                }
            return CouponRemainStatus.Success(result.parse(CouponInfoResponse.serializer()).convert())
        } catch (e: ClientRequestException) {
            return when (e.response.status) {
                HttpStatusCode(202, "") -> CouponRemainStatus.Success(
                    e.response.parse(CouponInfoResponse.serializer()).convert()
                )
                HttpStatusCode(
                    403,
                    "error"
                ) -> e.response.parse(CouponRemainStatus.Error.serializer())
                HttpStatusCode(
                    405,
                    "error"
                ) -> e.response.parse(CouponRemainStatus.Error.serializer())
                HttpStatusCode(
                    412,
                    "error"
                ) -> e.response.parse(CouponRemainStatus.Error.serializer())

                HttpStatusCode(
                    429,
                    ""
                ) -> e.response.parse(CouponRemainStatus.RequestLimited.serializer())

                HttpStatusCode(500, "error") -> CouponRemainStatus.ServerError
                HttpStatusCode(503, "error") -> CouponRemainStatus.ServerMaintenance

                else -> e.response.parse(CouponRemainStatus.Error.serializer())
            }
        }
    }
}