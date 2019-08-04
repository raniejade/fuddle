package fuddle.context

import fuddle.provider.Resource
import fuddle.provider.ResourceState
import kotlin.reflect.KClass

interface Context {
    fun <R: Resource<*>> defineResource(name: String, clz: KClass<R>, configure: R.() -> Unit): R
    fun <R: Resource<*>> defineResource(name: String, clz: KClass<R>, count: Int, configure: R.(Int) -> Unit): List<R>
}
