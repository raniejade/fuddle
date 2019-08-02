package fuddle.context

import fuddle.Resource
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class OptionalProperty<R: Resource, P>(default: P?): ReadWriteProperty<R, P?> {
    private var value: P? = default
    override fun getValue(thisRef: R, property: KProperty<*>): P? {
        return value
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: P?) {
        this.value = value
    }
}
