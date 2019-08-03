package provider

import fuddle.Resource
import fuddle.context.PropertyRegistry
import fuddle.provider.ResourceManager

class DBInstance(properties: PropertyRegistry): Resource(properties) {
    var name: String by required()
    var sourceDbIdentifier: String? by optional()

    // computed properties
    val id: String by computed { resource -> resource.name }
}

class DBInstanceResourceManager: ResourceManager<DBInstance> {
    override fun create(resource: DBInstance) {
        TODO()
    }

    override fun destroy(resource: DBInstance) {
        TODO()
    }

    override fun template(properties: PropertyRegistry): DBInstance {
        return DBInstance(properties)
    }

    override fun update(resource: DBInstance) {
        TODO()
    }
}
