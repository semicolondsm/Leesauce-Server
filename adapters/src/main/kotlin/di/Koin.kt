package di

import config.Config
import domain.repository.ResourceRepository
import io.ktor.application.*
import org.koin.core.instance.newInstance
import org.koin.dsl.module
import org.koin.dsl.single
import org.koin.ktor.ext.Koin
import repository.resource.ResourceRepositoryImpl
import usecase.usecases.icon.DeleteIconUsecase
import usecase.usecases.icon.GetIconListUsecase
import usecase.usecases.icon.UploadIconUsecase
import usecase.usecases.logo.DeleteLogoUsecase
import usecase.usecases.logo.GetLogoListUsecase
import usecase.usecases.logo.UploadLogoUsecase
import usecase.usecases.search.SearchUsecase
import usecase.usecases.source.DeleteSourceUsecase
import usecase.usecases.source.GetSourceListUsecase
import usecase.usecases.source.UploadSourceUsecase

fun Application.configureKoin(config: Config) {
    install(Koin) {
        modules(iconModules(config))
    }
}

@Suppress("USELESS_CAST")
private fun iconModules(config: Config) = module {
    single<UploadIconUsecase>()
    single<UploadLogoUsecase>()
    single<UploadSourceUsecase>()
    single<DeleteIconUsecase>()
    single<DeleteLogoUsecase>()
    single<DeleteSourceUsecase>()
    single<GetIconListUsecase>()
    single<GetLogoListUsecase>()
    single<GetSourceListUsecase>()
    single<SearchUsecase>()

    single { config.toEnvironment() }
    single<ResourceRepository> { params -> newInstance(ResourceRepositoryImpl::class, params)}
}
