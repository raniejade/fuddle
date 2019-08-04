package fuddle.provider

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Attribute<S: ResourceState, R: Resource<S>, P>(
    private val resolver: (S) -> P
): ReadOnlyProperty<R, P> {
    private lateinit var state: S

    override fun getValue(thisRef: R, property: KProperty<*>): P {
        return resolver(state)
    }

    internal fun resolveFrom(state: S) {
        this.state = state
    }
}
