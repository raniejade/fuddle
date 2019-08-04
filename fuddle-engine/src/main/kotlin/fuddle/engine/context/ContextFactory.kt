package fuddle.engine.context

import fuddle.engine.provider.ResourceRegistryImpl
import fuddle.engine.util.logger
import fuddle.provider.Provider
import java.util.*

object ContextFactory {
    private val providers by lazy {
        ServiceLoader.load(Provider::class.java).toList()
    }

    fun create(): ContextImpl {
        val resources = ResourceRegistryImpl()
        logger.info { "Available providers: ${providers.map(Provider::name)}" }
        providers.forEach { provider -> provider.load(resources) }
        return ContextImpl(resources)
    }
}
