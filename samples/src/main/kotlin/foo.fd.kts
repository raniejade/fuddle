import provider.DBInstance

// master: DBInstance
val master by resource<DBInstance> {
    name = "foo-master"
}

// replicas: List<DBInstance>
val replicas by resource<DBInstance>(3) {
    name = "foo-read-$it"
    sourceDbIdentifier = master.id
}
