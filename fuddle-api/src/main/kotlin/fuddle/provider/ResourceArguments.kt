package fuddle.provider

import kotlin.reflect.KProperty

abstract class ResourceArguments {
    inner class RequiredArgumentProvider<P: Any> {
        operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): RequiredArgument<P> {
            TODO()
        }
    }

    inner class OptionalArgumentProvider<P>(private val default: P?) {
        operator fun provideDelegate(resource: Any?, property: KProperty<*>): OptionalArgument<P> {
            TODO()
        }
    }

    protected fun <P: Any> required() = RequiredArgumentProvider<P>()
    protected fun <P> optional(default: P? = null) = OptionalArgumentProvider<P>(default)
}
