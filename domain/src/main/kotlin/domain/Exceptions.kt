package domain

open class BusinessException(val status: Int, override val message: String) : RuntimeException(message) {}

class PasswordNotMatchedException : BusinessException(401, "패스워드가 일치하지 않습니다.")
class IconAlreadyExistsException : BusinessException(409, "아이콘이 이미 존재합니다.")
class LogoAlreadyExistsException : BusinessException(409, "로고가 이미 존재합니다.")
class SourceAlreadyExistsException : BusinessException(409, "소스가 이미 존재합니다.")