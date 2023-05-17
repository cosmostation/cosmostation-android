package wannabit.io.cosmostaion.model

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Error(val message: String? = null, val exception: Throwable? = null): NetworkResult<Nothing>()
}
