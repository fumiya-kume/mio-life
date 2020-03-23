package nagoya.kuu.miolife.iijmio.remote.packetlog.infra

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
import nagoya.kuu.miolife.credential.AccessTokenRepository
import nagoya.kuu.miolife.iijmio.remote.packetlog.domain.LoadPacketLogUsecaseStatus
import nagoya.kuu.miolife.iijmio.remote.packetlog.domain.LoadPacketlogUsecase
import nagoya.kuu.miolife.iijmio.remote.packetlog.response.PacketLogRootResponse
import nagoya.kuu.miolife.iijmio.remote.packetlog.response.convert

class LoadPacketlogUsecaseImpl(
    private val accessTokenRepository: AccessTokenRepository
) :
    LoadPacketlogUsecase {
    private val httpClient = HttpClient(Android) {
        DefaultRequest {
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.unquoted)
        }
    }

    override suspend fun execute(): LoadPacketLogUsecaseStatus {
        suspend fun <T> HttpResponse.parse(targetType: KSerializer<T>): T {
            return Json(JsonConfiguration.Stable).parse(targetType, this.readText())
        }

        try {
            val accesstoken = accessTokenRepository.loadAccessToken() ?: ""
            val result =
                httpClient.get<HttpResponse>(Url("https://api.iijmio.jp/mobile/d/v2/log/packet/")) {
                    this.headers.append(
                        "X-IIJmio-Authorization",
                        accesstoken
                    )
                    this.headers.append("X-IIJmio-Developer", BuildConfig.DEVELOPER_ID)
                }
            return LoadPacketLogUsecaseStatus.Success(result.parse(PacketLogRootResponse.serializer()).convert())
        } catch (e: ClientRequestException) {
            return when (e.response.status) {
                HttpStatusCode(202, "") -> LoadPacketLogUsecaseStatus.Success(
                    e.response.parse(PacketLogRootResponse.serializer()).convert()
                )
                HttpStatusCode(
                    403,
                    "error"
                ) -> e.response.parse(LoadPacketLogUsecaseStatus.Error.serializer())
                HttpStatusCode(
                    405,
                    "error"
                ) -> e.response.parse(LoadPacketLogUsecaseStatus.Error.serializer())
                HttpStatusCode(
                    412,
                    "error"
                ) -> e.response.parse(LoadPacketLogUsecaseStatus.Error.serializer())

                HttpStatusCode(
                    429,
                    ""
                ) -> e.response.parse(LoadPacketLogUsecaseStatus.RequestLimited.serializer())

                HttpStatusCode(500, "error") -> LoadPacketLogUsecaseStatus.ServerError
                HttpStatusCode(503, "error") -> LoadPacketLogUsecaseStatus.ServerMaintenance

                else -> e.response.parse(LoadPacketLogUsecaseStatus.Error.serializer())
            }
        }
    }
}