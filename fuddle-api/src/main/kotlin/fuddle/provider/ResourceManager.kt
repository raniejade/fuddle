package fuddle.provider

interface ResourceManager<S: ResourceState, R: Resource<S>> {
    fun template(): R
    fun create(resource: R): S
    fun update(local: S, remote: S): S
    fun destroy(resource: R)
}
