package fuddle.context

import fuddle.provider.Resource
import fuddle.provider.ResourceArguments
import kotlin.reflect.KClass

interface Context {
    fun <A: ResourceArguments, R: Resource<*, A>> defineResource(name: String, clz: KClass<R>, configure: A.() -> Unit): R
    fun <A: ResourceArguments, R: Resource<*, A>> defineResource(name: String, clz: KClass<R>, count: Int, configure: A.(Int) -> Unit): List<R>
    fun <V> getVariable(name: String, clz: KClass<*>): V?
}
