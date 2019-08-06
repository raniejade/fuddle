package fuddle

import fuddle.context.Context
import fuddle.provider.Resource

inline fun <reified R: Resource<*>> Context.resource(noinline configure: R.() -> Unit) = ResourceProvider(this, R::class, configure)
inline fun <reified R: Resource<*>> Context.resource(count: Int, noinline configure: R.(Int) -> Unit) = ListResourceProvider(this, R::class, count, configure)

fun <V: Any> Context.required() = RequiredVariableProvider<V>(this)
fun <V: Any?> Context.optional(default: V) = OptionalVariableProvider(this, default)
