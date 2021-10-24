package usecase.usecases

import domain.PasswordNotMatchedException

/**
 * @author smoothbear
 * Can not be extended at outside of module.
 */
sealed class UsecaseType {
    fun auth(password: String) {
        if (!System.getenv("ADMIN_PASSWORD").equals(password))
            throw PasswordNotMatchedException()
    }
}

abstract class NormalUsecase<T: Any, R: Any> : UsecaseType(), suspend (T) -> R {
    protected abstract suspend fun executor(request: T): R
    override suspend fun invoke(request: T): R = executor(request)
}

abstract class AuthUsecase<T: Any, R: Any> : UsecaseType(), suspend (T, String) -> R {
    protected abstract suspend fun executor(request: T): R
    override suspend fun invoke(request: T, password: String): R {
        auth(password)
        return executor(request)
    }
}