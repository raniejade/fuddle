package fuddle.provider

import kotlin.reflect.KProperty

abstract class Resource<S: ResourceState, A: ResourceArguments> {
    private val arguments = mutableMapOf<String, ReadOnlyArgument<S, Resource<S, A>, *>>()
    private val attributes = mutableMapOf<String, Attribute<S, Resource<S, A>, *>>()

    inner class ReadOnlyArgumentsProvider<R: Resource<S, A>, P>(
        private val resolver: (S) -> P
    ) {
        operator fun provideDelegate(resource: Resource<S, A>, property: KProperty<*>): ReadOnlyArgument<S, R, P> {
            val argument = ReadOnlyArgument<S, R, P>(resolver)
            arguments[property.name] = argument as ReadOnlyArgument<S, Resource<S, A>, P>
            return argument
        }
    }

    inner class AttributeProvider<R: Resource<S, A>, P>(
        private val resolver: (S) -> P
    ) {
        operator fun provideDelegate(resource: Resource<S, A>, property: KProperty<*>): Attribute<S, R, P> {
            val attribute = Attribute<S, R, P>(resolver)
            attributes[property.name] = attribute as Attribute<S, Resource<S, A>, P>
            return attribute
        }
    }

    protected fun <R: Resource<S, A>, P> argument(resolver: (S) -> P) = ReadOnlyArgumentsProvider<R, P>(resolver)
    protected fun <R: Resource<S, A>, P> attribute(resolver: (S) -> P) = AttributeProvider<R, P>(resolver)
    protected abstract fun createArguments(): A

    internal fun resolveArguments(state: S) {
        arguments.values.forEach {
            it.resolveFrom(state)
        }
    }

    internal fun resolveAttributes(state: S) {
        attributes.values.forEach {
            it.resolveFrom(state)
        }
    }
}
