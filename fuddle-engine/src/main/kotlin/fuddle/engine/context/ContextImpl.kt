package fuddle.engine.context

import fuddle.context.Context
import fuddle.engine.provider.ResourceRegistryImpl
import fuddle.engine.util.logger
import fuddle.provider.Resource
import fuddle.provider.ResourceState
import kotlin.reflect.KClass

class ContextImpl(private val resourceRegistry: ResourceRegistryImpl): Context {
    internal data class ResourceDefinition<R : Resource<*>>(
        val resource: List<R>,
        val configure: R.(Int) -> Unit
    )

    private val definitions = mutableMapOf<String, ResourceDefinition<*>>()

    override fun <R : Resource<*>> defineResource(name: String, clz: KClass<R>, configure: R.() -> Unit): R {
        return defineResource(name, clz, 0) {
            configure()
        }.first()
    }

    override fun <R : Resource<*>> defineResource(name: String, clz: KClass<R>, count: Int, configure: R.(Int) -> Unit): List<R> {
        val resources = (0 until count).map { resourceRegistry.getManager(clz).template() }
        definitions[name] = ResourceDefinition(resources, configure)
        return resources
    }

    fun loadLocalState() {
        logger.info { "Loading local state..." }
    }

    fun refreshRemoteState() {
        logger.info { "Refreshing remote state..." }
    }
}
