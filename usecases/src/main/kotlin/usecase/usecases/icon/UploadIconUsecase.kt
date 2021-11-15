package usecase.usecases.icon

import domain.IconAlreadyExistsException
import domain.entity.Resource
import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.Environment
import usecase.model.DefaultResponse
import usecase.model.UploadIconRequest
import usecase.usecases.AuthUsecase
import usecase.usecases.common.writeFile
import java.io.File

class UploadIconUsecase (
    private val repository: ResourceRepository,
    private val environment: Environment
) : AuthUsecase<UploadIconRequest, DefaultResponse>() {
    override suspend fun executor(request: UploadIconRequest): DefaultResponse {

        val file = request.file
        val iconName = request.iconName
        val uploadDir = environment.uploadDir

        if (repository.findByNameAndType(iconName, ResourceType.ICON) != null)
            throw IconAlreadyExistsException()

        val ext = File(file.originalFileName!!).extension
        val pathName = "$uploadDir/icon/$iconName.$ext"

        writeFile(file, pathName)

        repository.create(
            Resource(
                name = iconName,
                url = "${environment.baseUrl}/icon/$iconName.$ext",
                type = ResourceType.ICON
            )
        )

        return DefaultResponse("아이콘이 성공적으로 등록되었습니다.")
    }
}