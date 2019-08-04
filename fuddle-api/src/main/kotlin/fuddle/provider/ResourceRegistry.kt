package fuddle.provider

import kotlin.reflect.KClass

abstract class ResourceRegistry {
    abstract fun <R: Resource<*>> registerResource(clz: KClass<R>, manager: ResourceManager<*, R>)

    inline fun <reified R: Resource<*>> registerResource(manager: ResourceManager<*, R>)
        = registerResource(R::class, manager)
}
