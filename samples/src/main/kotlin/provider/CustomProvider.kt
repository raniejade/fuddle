package provider

import fuddle.provider.Provider
import fuddle.provider.ResourceRegistry

class CustomProvider: Provider {
    override fun load(registry: ResourceRegistry) {
        registry.registerResource(DBInstanceResourceManager())
    }
}
