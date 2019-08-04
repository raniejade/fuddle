package fuddle.provider

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RequiredArgument<R: Resource<*>, P: Any>: ReadWriteProperty<R, P> {
    private var value by Delegates.notNull<P>()

    override fun getValue(thisRef: R, property: KProperty<*>): P {
        return value
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: P) {
        this.value = value
    }
}
