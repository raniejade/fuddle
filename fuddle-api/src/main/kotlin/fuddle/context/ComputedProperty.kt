package fuddle.context

import fuddle.Resource
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ComputedProperty<R: Resource, P>(val resolver: (R) -> P): ReadOnlyProperty<R, P> {
    override fun getValue(thisRef: R, property: KProperty<*>): P {
        TODO()
    }
}
