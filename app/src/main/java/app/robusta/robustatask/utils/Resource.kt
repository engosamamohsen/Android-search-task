package app.robusta.robustatask.utils


sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class AllowEdit<T>() : Resource<T>()
    class StopEdit<T>() : Resource<T>()
    class TOP<T>() : Resource<T>()
}