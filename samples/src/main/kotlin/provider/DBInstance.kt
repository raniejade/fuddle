package provider

import fuddle.provider.Resource
import fuddle.provider.ResourceManager
import fuddle.provider.ResourceState

class DBInstance: Resource<DBInstanceState>() {
    var name: String by required()
    var sourceDbIdentifier: String? by optional()
    val id: String by attribute(DBInstanceState::id)

    override fun applyArguments(state: DBInstanceState) {
        this.name = state.name
        this.sourceDbIdentifier = state.sourceDbIdentifier
    }
}

class DBInstanceState(
    var name: String,
    var sourceDbIdentifier: String?,
    val id: String
): ResourceState()

class DBInstanceResourceManager: ResourceManager<DBInstanceState, DBInstance> {
    override fun create(resource: DBInstance): DBInstanceState {
        TODO()
    }

    override fun update(local: DBInstanceState, remote: DBInstanceState): DBInstanceState {
        TODO()
    }

    override fun destroy(resource: DBInstance) {
        TODO()
    }

    override fun template(): DBInstance {
        return DBInstance()
    }
}
