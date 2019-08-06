import provider.DBInstance

val service: String by optional(default = "foo")

// master: DBInstance
val master by resource<DBInstance> {
    name = "$service-master"
}

// replicas: List<DBInstance>
val replicas by resource<DBInstance>(3) {
    name = "$service-read-$it"
    sourceDbIdentifier = master.id
}
