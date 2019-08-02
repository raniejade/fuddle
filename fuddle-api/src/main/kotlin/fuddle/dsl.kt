package fuddle

import fuddle.context.Context

inline fun <reified R: Resource> Context.resource(noinline configure: R.() -> Unit) = ResourceProvider(this, R::class, configure)
inline fun <reified R: Resource> Context.resource(count: Int, noinline configure: R.(Int) -> Unit) = ListResourceProvider(this, R::class, count, configure)
