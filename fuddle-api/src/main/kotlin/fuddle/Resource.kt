package fuddle

import fuddle.context.PropertyRegistry

abstract class Resource(private val registry: PropertyRegistry) {
    protected fun <R: Resource, P: Any> required() = registry.required<R, P>()
    protected fun <R: Resource, P> optional(default: P? = null) = registry.optional<R, P>(default)
    protected fun <R: Resource, P> computed(resolver: (R) -> P) = registry.computed(resolver)
}
