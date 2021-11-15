package usecase.usecases.common

import io.ktor.http.content.*
import java.io.File

fun writeFile(requestFile: PartData.FileItem, filePath: String) {
    val savedFile = File(filePath)

    requestFile.streamProvider().use { its -> savedFile.outputStream().buffered().use { its.copyTo(it) } }
    requestFile.dispose()
}