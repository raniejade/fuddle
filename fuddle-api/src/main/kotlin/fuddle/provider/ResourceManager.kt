package fuddle.provider

interface ResourceManager<S: ResourceState, A: ResourceArguments, R: Resource<S, A>> {
    fun template(): R
}
