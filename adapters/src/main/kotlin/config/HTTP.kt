package config

import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.http.*
import io.ktor.application.*

fun Application.configureHTTP() {
    install(CachingHeaders) {
        options { outgoingContent ->
            when (outgoingContent.contentType?.withoutParameters()) {
                ContentType.Image.Any -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60))
                else -> null
            }
        }
    }
}