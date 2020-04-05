package kuu.nagoya.core.config

sealed class RepositoryResponse<out T : Any, out exception : Throwable> {
    object Loading : RepositoryResponse<Nothing, Nothing>()
    data class Success<T : Any>(val value: T) : RepositoryResponse<T, Nothing>()
    data class Error<T : Throwable>(val exception: T) : RepositoryResponse<Nothing, T>()
}