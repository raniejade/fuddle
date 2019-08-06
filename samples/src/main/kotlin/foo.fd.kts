import provider.DBInstance

val flavor: String by required()

val master: DBInstance by resource {
    name = "foo-master"
    tags = mutableMapOf(
        "flavor" to flavor,
        "role" to "master"
    )
}

val replicas: List<DBInstance> by resource(3) {
    name = "foo-read-$it"
    sourceDbIdentifier = master.id
    tags = mutableMapOf(
        "flavor" to flavor,
        "role" to "replica"
    )
}
