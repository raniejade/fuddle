package provider

import fuddle.provider.Provider
import fuddle.provider.ResourceRegistry

class CustomProvider: Provider {
    override val name = "custom"
    override fun load(registry: ResourceRegistry) {
        registry.registerResource(DBInstanceResourceManager())
    }
}
