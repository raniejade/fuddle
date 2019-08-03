package fuddle.provider

import fuddle.Resource
import fuddle.context.PropertyRegistry

interface ResourceManager<R: Resource> {
    fun template(properties: PropertyRegistry): R
    fun create(resource: R)
    fun update(resource: R)
    fun destroy(resource: R)
}
