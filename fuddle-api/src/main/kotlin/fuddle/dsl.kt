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

inline fun <reified T> required(): ReadWriteProperty<Resource, T> {
    TODO()
}

inline fun <reified T> optional(default: T? = null): ReadWriteProperty<Resource, T?> {
    TODO()
}

class Database: Resource {
    var name: String by required()
    var sourceDbIdentifier: String? by optional()
}
