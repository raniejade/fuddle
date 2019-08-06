package fuddle.provider

interface ResourceManager<S: ResourceState, A: ResourceArguments, R: Resource<S, A>> {
    fun template(): R
    fun create(resource: R): S
    fun update(local: S, remote: S): S
    fun destroy(resource: R)
}
