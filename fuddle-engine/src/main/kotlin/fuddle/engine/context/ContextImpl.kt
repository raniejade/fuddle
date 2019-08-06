package fuddle.engine.context

import fuddle.context.Context
import fuddle.engine.provider.ResourceRegistryImpl
import fuddle.engine.util.logger
import fuddle.provider.Resource
import fuddle.provider.ResourceArguments
import fuddle.provider.ResourceState
import kotlin.reflect.KClass

class ContextImpl(private val resourceRegistry: ResourceRegistryImpl): Context {
    internal data class ResourceDefinition<A: ResourceArguments, R : Resource<*, A>>(
        val resource: List<R>,
        val configure: A.(Int) -> Unit
    )

    private val definitions = mutableMapOf<String, ResourceDefinition<*, *>>()

    override fun <A : ResourceArguments, R : Resource<*, A>> defineResource(name: String, clz: KClass<R>, configure: A.() -> Unit): R {
        return defineResource(name, clz, 0) {
            configure()
        }.first()
    }

    override fun <A : ResourceArguments, R : Resource<*, A>> defineResource(name: String, clz: KClass<R>, count: Int, configure: A.(Int) -> Unit): List<R> {
        val resources = (0 until count).map { resourceRegistry.getManager(clz).template() }
        definitions[name] = ResourceDefinition(resources, configure)
        return resources
    }

    override fun <V> getRequiredVar(name: String): V? {
        TODO()
    }

    fun loadLocalState() {
        logger.info { "Loading local state..." }
    }

    fun refreshRemoteState() {
        logger.info { "Refreshing remote state..." }
    }
}
