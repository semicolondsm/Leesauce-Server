package domain

open class BusinessException(val status: Int, override val message: String) : RuntimeException(message) {}

class PasswordNotMatchedException : BusinessException(401, "password is not matched.")