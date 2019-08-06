package fuddle.provider

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RequiredArgument<P: Any>: ReadWriteProperty<Any?, P> {
    private var value by Delegates.notNull<P>()

    override fun getValue(thisRef: Any?, property: KProperty<*>): P {
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: P) {
        this.value = value
    }
}
