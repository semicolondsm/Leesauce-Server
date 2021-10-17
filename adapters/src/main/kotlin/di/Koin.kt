package di

import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(iconModules())
    }
}

@Suppress("USELESS_CAST")
private fun iconModules() = module {

}
