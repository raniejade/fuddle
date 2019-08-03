package fuddle.engine.provider

import fuddle.Resource
import fuddle.provider.ResourceManager
import fuddle.provider.ResourceRegistry
import kotlin.reflect.KClass

class ResourceRegistryImpl: ResourceRegistry() {
    private val resources = mutableMapOf<KClass<out Resource>, ResourceManager<out Resource>>()

    override fun <R : Resource> registerResource(clz: KClass<R>, manager: ResourceManager<R>) {
        resources[clz] = manager
    }

    fun <R : Resource> getManager(clz: KClass<R>): ResourceManager<R> {
        return resources[clz] as ResourceManager<R>
    }
}
