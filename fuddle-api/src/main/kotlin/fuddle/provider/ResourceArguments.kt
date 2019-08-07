package fuddle.provider

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class ResourceArguments {
    class Info(val forceNew: Boolean, val nullable: Boolean)
    inner class ArgumentProvider<V>(private val info: Info,
                              private val default: V?) {
        operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ReadWriteProperty<Any?, V> {
            argumentInfos[property.name] = info

            return object: ReadWriteProperty<Any?, V> {
                private var value: V? = null
                override fun setValue(thisRef: Any?, property: KProperty<*>, value: V) {
                    TODO()
                }

                override fun getValue(thisRef: Any?, property: KProperty<*>): V {
                    var returnValue = value
                    if (!info.nullable && returnValue == null) {
                        if (default == null) {
                            throw IllegalArgumentException("Required property ${property.name} not set.")
                        }
                        returnValue = default
                    }

                    return returnValue as V
                }
            }
        }
    }

    private val argumentInfos = mutableMapOf<String, Info>()

    protected inline fun <reified A> argument(forceNew: Boolean = false, default: A? = null) = ArgumentProvider(Info(forceNew, null is A), default)

    fun isArgumentForceNew(name: String): Boolean {
        val info = checkNotNull(argumentInfos[name]) { "Unknown argument $name" }
        return info.forceNew
    }
}
