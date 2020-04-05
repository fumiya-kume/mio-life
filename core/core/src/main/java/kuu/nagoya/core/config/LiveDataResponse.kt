package kuu.nagoya.core.config

sealed class LiveDataResponse<out T : Any, out exception : Throwable> {

    companion object {
        fun <T : Any, U : Any, E : Throwable> from(
            repositoryResponse: RepositoryResponse<T, E>,
            lambda: ((source: T) -> U)
        ): LiveDataResponse<U, E> {
            return when (repositoryResponse) {
                RepositoryResponse.Loading -> Loading
                is RepositoryResponse.Success -> Success(
                    lambda(repositoryResponse.value)
                )
                is RepositoryResponse.Error -> Error(
                    repositoryResponse.exception
                )
            }
        }
    }

    object Loading : LiveDataResponse<Nothing, Nothing>()
    data class Success<T : Any>(val value: T) : LiveDataResponse<T, Nothing>()
    data class Error<T : Throwable>(val exception: T) : LiveDataResponse<Nothing, T>()
}