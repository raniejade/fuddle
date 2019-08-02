package fuddle.context

import fuddle.Resource
import kotlin.reflect.KClass

interface Context {
    fun <R: Resource> createResource(name: String, clz: KClass<R>, configure: R.() -> Unit): R
    fun <R: Resource> createResources(name: String, clazz: KClass<R>, count: Int, configure: R.(Int) -> Unit): List<R>
}
