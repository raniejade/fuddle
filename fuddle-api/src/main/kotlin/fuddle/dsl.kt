package fuddle

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface Context

interface Resource

interface ResourceDelegate<R: Resource>: ReadOnlyProperty<Any?, R>

interface ResourceProvider<R: Resource> {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ResourceDelegate<R> {
        TODO()
    }
}

interface ListResourceDelegate<R: Resource>: ReadOnlyProperty<Any?, List<R>>

interface ListResourceProvider<R: Resource> {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ListResourceDelegate<R> {
        TODO()
    }
}

fun <R: Resource>  Context.resource(configure: R.() -> Unit): ResourceProvider<R> {
    TODO()
}

fun <R: Resource>  Context.resource(count: Int, configure: R.(Int) -> Unit): ListResourceProvider<R> {
    TODO()
}

inline fun <reified R: Resource, reified P> required(): ReadWriteProperty<R, P> {
    TODO()
}

inline fun <reified R: Resource, reified P> optional(default: P? = null): ReadWriteProperty<R, P?> {
    TODO()
}

inline fun <reified R: Resource, reified P> computed(): ReadOnlyProperty<R, P> {
    TODO()
}

class Database: Resource {
    var name: String by required()
    var sourceDbIdentifier: String? by optional()
    val id: String by computed()
}
