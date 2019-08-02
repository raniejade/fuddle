package fuddle

import fuddle.context.Context
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ResourceDelegate<R: Resource>(private val resource: R): ReadOnlyProperty<Any?, R> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): R {
        return resource
    }
}

class ResourceProvider<R: Resource>(private val context: Context,
                                           private val clz: KClass<R>,
                                           private val configure: R.() -> Unit) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ResourceDelegate<R> {
        return ResourceDelegate(context.createResource(property.name, clz, configure))
    }
}

class ListResourceDelegate<R: Resource>(private val resources: List<R>): ReadOnlyProperty<Any?, List<R>> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): List<R> {
        return resources
    }
}

class ListResourceProvider<R: Resource>(private val context: Context,
                                               private val clz: KClass<R>,
                                               private val count: Int,
                                               private val configure: R.(Int) -> Unit) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ListResourceDelegate<R> {
        return ListResourceDelegate(context.createResources(property.name, clz, count, configure))
    }
}
