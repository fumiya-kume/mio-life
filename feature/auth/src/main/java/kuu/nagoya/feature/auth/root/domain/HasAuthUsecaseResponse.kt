package kuu.nagoya.feature.auth.root.domain

internal sealed class HasAuthUsecaseResponse {
    object Loading : HasAuthUsecaseResponse()
    object HasAuth : HasAuthUsecaseResponse()
    object NotHasAuth : HasAuthUsecaseResponse()
    data class Error(val message: String = "") : HasAuthUsecaseResponse()
}