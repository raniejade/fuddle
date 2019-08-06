package fuddle

import fuddle.context.Context
import fuddle.provider.Resource
import fuddle.provider.ResourceArguments
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ResourceDelegate<R: Resource<*, *>>(private val resource: R): ReadOnlyProperty<Any?, R> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): R {
        return resource
    }
}

class ResourceProvider<A: ResourceArguments, R: Resource<*, A>>(private val context: Context,
                                                                private val clz: KClass<R>,
                                                                private val configure: A.() -> Unit) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ResourceDelegate<R> {
        return ResourceDelegate(context.defineResource(property.name, clz, configure))
    }
}

class ListResourceDelegate<R: Resource<*, *>>(private val resources: List<R>): ReadOnlyProperty<Any?, List<R>> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): List<R> {
        return resources
    }
}

class ListResourceProvider<A: ResourceArguments, R: Resource<*, A>>(private val context: Context,
                                                                    private val clz: KClass<R>,
                                                                    private val count: Int,
                                                                    private val configure: A.(Int) -> Unit) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ListResourceDelegate<R> {
        return ListResourceDelegate(context.defineResource(property.name, clz, count, configure))
    }
}

class RequiredVariableDelegate<V: Any>(private val value: V): ReadOnlyProperty<Any?, V> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): V {
        return value
    }
}

class RequiredVariableProvider<V: Any>(private val context: Context) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): RequiredVariableDelegate<V> {
        val value = checkNotNull(context.getVariable<V>(property.name))
        return RequiredVariableDelegate(value)
    }
}

class OptionalVariableDelegate<V: Any?>(private val value: V): ReadOnlyProperty<Any?, V> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): V {
        return value
    }
}

class OptionalVariableProvider<V: Any?>(private val context: Context,
                                        private val default: V) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): OptionalVariableDelegate<V> {
        val value = context.getVariable(property.name) ?: default
        return OptionalVariableDelegate(value)
    }
}
