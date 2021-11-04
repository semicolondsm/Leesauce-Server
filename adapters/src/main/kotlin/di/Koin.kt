package di

import config.Config
import io.ktor.application.*
import org.koin.core.instance.newInstance
import org.koin.dsl.module
import org.koin.dsl.single
import org.koin.ktor.ext.Koin
import usecase.usecases.icon.UploadIconUsecase

fun Application.configureKoin(config: Config) {
    install(Koin) {
        modules(iconModules(config))
    }
}

@Suppress("USELESS_CAST")
private fun iconModules(config: Config) = module {
    single { UploadIconUsecase(config.uploadDir) }
}
