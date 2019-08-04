package fuddle.provider

import kotlin.reflect.KProperty

abstract class Resource<S: ResourceState> {
    private val attributes = mutableMapOf<String, Attribute<S, Resource<S>, *>>()

    inner class RequiredArgumentProvider<R: Resource<S>, P: Any> {
        operator fun provideDelegate(resource: Resource<S>, property: KProperty<*>): RequiredArgument<R, P> {
            TODO()
        }
    }

    inner class OptionalArgumentProvider<R: Resource<S>, P>(private val default: P?) {
        operator fun provideDelegate(resource: Resource<S>, property: KProperty<*>): OptionalArgument<R, P> {
            TODO()
        }
    }

    inner class AttributeProvider<R: Resource<S>, P>(
        private val resolver: (S) -> P
    ) {
        operator fun provideDelegate(resource: Resource<S>, property: KProperty<*>): Attribute<S, R, P> {
            val attribute = Attribute<S, R, P>(resolver)
            attributes[property.name] = attribute as Attribute<S, Resource<S>, P>
            return attribute
        }
    }

    protected fun <R: Resource<S>, P: Any> required() = RequiredArgumentProvider<R, P>()
    protected fun <R: Resource<S>, P> optional(default: P? = null) = OptionalArgumentProvider<R, P>(default)
    protected fun <R: Resource<S>, P> attribute(resolver: (S) -> P) = AttributeProvider<R, P>(resolver)
    protected abstract fun applyArguments(state: S)

    internal fun apply(state: S) {
        applyArguments(state)
    }

    internal fun resolveAttributes(state: S) {
        attributes.values.forEach {
            it.resolveFrom(state)
        }
    }
}
