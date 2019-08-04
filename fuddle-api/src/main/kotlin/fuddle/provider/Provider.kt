package fuddle.provider

interface Provider {
    val name: String
    fun load(registry: ResourceRegistry)
}
