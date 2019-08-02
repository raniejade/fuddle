package provider

import fuddle.Resource
import fuddle.computed
import fuddle.optional
import fuddle.required


class DBInstance: Resource {
    var name: String by required()
    var sourceDbIdentifier: String? by optional()
    val id: String by computed()
}

