package usecase.usecases

/**
 * @author smoothbear
 * Can not be extended at outside of module.
 */
sealed class UsecaseType {
    /**
     * authentication logics..
     */
}

abstract class Usecase<T: Any, R: Any> : UsecaseType(), suspend (T) -> R {
    protected abstract suspend fun executor(request: T): R
    override suspend fun invoke(request: T): R = executor(request)
}