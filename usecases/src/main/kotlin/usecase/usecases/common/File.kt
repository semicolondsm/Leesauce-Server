package usecase.usecases.common

import io.ktor.http.content.*
import java.io.File

fun writeFile(requestFile: PartData.FileItem, filePath: String) {
    val savedFile = File(filePath)

    val directoryPath = filePath.substring(0, filePath.lastIndexOf("/"))
    val directory = File(directoryPath)

    if (!directory.exists()) {
        directory.mkdir()
    }

    requestFile.streamProvider().use { its -> savedFile.outputStream().buffered().use { its.copyTo(it) } }
    requestFile.dispose()
}

fun deleteFile(filePath: String) {
    val file = File(filePath)

    file.delete()
}