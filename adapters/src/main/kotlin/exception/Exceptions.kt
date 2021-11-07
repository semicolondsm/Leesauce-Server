package exception

// Handler exceptions
class PasswordNeedException : Exception("this api requires password to use.")
class FileNotFoundException : Exception("file is not found.")
class FileNameIsNeededException : Exception("file name is needed.")