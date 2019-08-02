# fuddle

```kotlin
val master by resource<Database> {
    name = "foo-master"
}

val replicas by resource<Database>(3) {
    name = "foo-read-$it"
    sourceDbIdentifier = master.id
}
```
