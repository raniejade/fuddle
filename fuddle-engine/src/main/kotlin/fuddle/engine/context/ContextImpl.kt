package fuddle.engine.context

import fuddle.Resource
import fuddle.context.Context
import fuddle.engine.provider.ResourceRegistryImpl
import kotlin.reflect.KClass

class ContextImpl(private val resources: ResourceRegistryImpl): Context {
    override fun <R : Resource> defineResource(name: String, clz: KClass<R>, configure: R.() -> Unit): R {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <R : Resource> defineResource(name: String, clazz: KClass<R>, count: Int, configure: R.(Int) -> Unit): List<R> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
