package fuddle.context

import fuddle.Resource
import kotlin.reflect.KProperty

class PropertyRegistry {
    inner class RequiredPropertyProvider<R: Resource, P: Any> {
        operator fun provideDelegate(resource: Resource, property: KProperty<*>): RequiredProperty<R, P> {
            TODO()
        }
    }

    inner class OptionalPropertyProvider<R: Resource, P>(private val default: P?) {
        operator fun provideDelegate(resource: Resource, property: KProperty<*>): OptionalProperty<R, P> {
            TODO()
        }
    }

    inner class ComputedPropertyProvider<R: Resource, P>(private val resolver: (R) -> P) {
        operator fun provideDelegate(resource: Resource, property: KProperty<*>): ComputedProperty<R, P> {
            TODO()
        }
    }

    fun <R: Resource, P: Any> required() = RequiredPropertyProvider<R, P>()
    fun <R: Resource, P> optional(default: P?) = OptionalPropertyProvider<R, P>(default)
    fun <R: Resource, P> computed(resolver: (R) -> P) = ComputedPropertyProvider<R, P>(resolver)
}
