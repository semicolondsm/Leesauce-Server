package exception

open class ValidationException(override val message: String) : Exception(message)

// Handler exceptions
class PasswordNeedException : ValidationException("this api requires password to use.")
class FileNotFoundException : ValidationException("file is not found.")
class FileNameIsNeededException : ValidationException("file name is needed.")
class NameIsNeededException : ValidationException("name is needed.")