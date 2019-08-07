package fuddle.provider

import kotlin.reflect.KProperty

abstract class ResourceArguments {
    class Info(val forceNew: Boolean)
    inner class RequiredArgumentProvider<P: Any>(private val info: Info) {
        operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): RequiredArgument<P> {
            argumentInfos[property.name] = info
            return RequiredArgument()
        }
    }

    inner class OptionalArgumentProvider<P>(private val info: Info,
                                            private val default: P?) {
        operator fun provideDelegate(resource: Any?, property: KProperty<*>): OptionalArgument<P> {
            argumentInfos[property.name] = info
            return OptionalArgument(default)
        }
    }

    private val argumentInfos = mutableMapOf<String, Info>()

    protected fun <P: Any> required(forceNew: Boolean = false) = RequiredArgumentProvider<P>(Info(forceNew))
    protected fun <P> optional(forceNew: Boolean = false, default: P? = null) = OptionalArgumentProvider<P>(Info(forceNew), default)

    fun isArgumentForceNew(name: String): Boolean {
        val info = checkNotNull(argumentInfos[name]) { "Unknown argument $name" }
        return info.forceNew
    }
}
