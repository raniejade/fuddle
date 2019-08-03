package fuddle

import fuddle.context.PropertyRegistry

abstract class Resource(private val properties: PropertyRegistry) {
    protected fun <R: Resource, P: Any> required() = properties.required<R, P>()
    protected fun <R: Resource, P> optional(default: P? = null) = properties.optional<R, P>(default)
    protected fun <R: Resource, P> computed(resolver: (R) -> P) = properties.computed(resolver)
}
