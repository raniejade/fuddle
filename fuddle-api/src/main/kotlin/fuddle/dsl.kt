package fuddle

import fuddle.context.Context
import fuddle.provider.Resource
import fuddle.provider.ResourceArguments

inline fun <A: ResourceArguments, reified R: Resource<*, A>> Context.resource(noinline configure: A.() -> Unit) = ResourceProvider(this, R::class, configure)
inline fun <A: ResourceArguments, reified R: Resource<*, A>> Context.resource(count: Int, noinline configure: A.(Int) -> Unit) = ListResourceProvider(this, R::class, count, configure)

fun <V: Any> Context.required() = RequiredVariableProvider<V>(this)
fun <V: Any?> Context.optional(default: V) = OptionalVariableProvider(this, default)
