package fuddle.provider

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class OptionalArgument<P>(default: P?): ReadWriteProperty<Any?, P?> {
    private var value: P? = default
    override fun getValue(thisRef: Any?, property: KProperty<*>): P? {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: P?) {
        this.value = value
    }
}
