package provider

import fuddle.Resource
import fuddle.context.PropertyRegistry

class DBInstance(registry: PropertyRegistry): Resource(registry) {
    var name: String by required()
    var sourceDbIdentifier: String? by optional()

    // computed properties
    val id: String by computed { resource -> resource.name }
}

