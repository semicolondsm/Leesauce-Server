package usecase.usecases.icon

import usecase.model.IconResultResponse
import usecase.model.UploadIconRequest
import usecase.usecases.AuthUsecase

class UploadIconUsecase : AuthUsecase<UploadIconRequest, IconResultResponse>() {
    override suspend fun executor(request: UploadIconRequest): IconResultResponse {
        request.iconName
    }
}