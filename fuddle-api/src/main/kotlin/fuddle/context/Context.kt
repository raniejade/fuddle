package fuddle.context

import fuddle.Resource
import kotlin.reflect.KClass

interface Context {
    fun <R: Resource> defineResource(name: String, clz: KClass<R>, configure: R.() -> Unit): R
    fun <R: Resource> defineResource(name: String, clazz: KClass<R>, count: Int, configure: R.(Int) -> Unit): List<R>
}
