package provider

import fuddle.provider.Resource
import fuddle.provider.ResourceArguments
import fuddle.provider.ResourceManager
import fuddle.provider.ResourceState

class DBInstance: Resource<DBInstanceState, DBInstanceArguments>() {
    val id by argument(DBInstanceState::id)
    val name by argument(DBInstanceState::name)
    val tags by argument(DBInstanceState::tags)
    val sourceDbIdentifier by attribute(DBInstanceState::sourceDbIdentifier)

    override fun createArguments(): DBInstanceArguments {
        return DBInstanceArguments()
    }
}

class DBInstanceState(
    var name: String,
    var sourceDbIdentifier: String?,
    var tags: Map<String, String>,
    val id: String
): ResourceState()

class DBInstanceArguments: ResourceArguments() {
    var name: String by required()
    var sourceDbIdentifier: String? by optional()
    var tags: Map<String, String>? by optional()
}

class DBInstanceResourceManager: ResourceManager<DBInstanceState, DBInstanceArguments, DBInstance> {
    override fun template(): DBInstance {
        TODO()
    }
}
