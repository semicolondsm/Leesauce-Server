package usecase.usecases.logo

import domain.LogoAlreadyExistsException
import domain.entity.Resource
import domain.entity.ResourceType
import domain.repository.ResourceRepository
import usecase.Environment
import usecase.model.DefaultResponse
import usecase.model.UploadLogoRequest
import usecase.usecases.AuthUsecase
import usecase.usecases.common.writeFile
import java.io.File

class UploadLogoUsecase (
    private val repository: ResourceRepository,
    private val environment: Environment
) : AuthUsecase<UploadLogoRequest, DefaultResponse>() {
    override suspend fun executor(request: UploadLogoRequest): DefaultResponse {

        val file = request.file
        val logoName = request.logoName
        val uploadDir = environment.uploadDir

        if (repository.findByNameAndType(logoName, ResourceType.LOGO) != null)
            throw LogoAlreadyExistsException()

        val ext = File(file.originalFileName!!).extension
        val pathName = "$uploadDir/logo/$logoName.$ext"

        writeFile(file, pathName)

        repository.create(
            Resource(
                name = logoName,
                url = "${environment.baseUrl}/logo/$logoName.$ext",
                type = ResourceType.LOGO
            )
        )

        return DefaultResponse("로고가 성공적으로 등록되었습니다.")
    }
}