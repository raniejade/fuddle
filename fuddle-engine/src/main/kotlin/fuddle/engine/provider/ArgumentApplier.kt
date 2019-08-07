package fuddle.engine.provider

import fuddle.provider.ResourceArguments
import fuddle.provider.ResourceState
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty

class ArgumentApplier {
    sealed class Action<S: ResourceState>(val state: ResourceState) {
        class Create<S: ResourceState>(state: S): Action<S>(state)
        class Update<S: ResourceState>(state: S, val changes: List<String>): Action<S>(state)
    }

    private class ArgumentCache(val arguments: Map<String, KMutableProperty<*>>)
    private val caches = mutableMapOf<KClass<out ResourceState>, ArgumentCache>()

    fun <S: ResourceState, A: ResourceArguments> apply(state: S, arguments: A): Action<S> {
        val argumentCache = getMapper(state::class, arguments::class)

        val changedArguments = mutableListOf<String>()
        var forceNew = false
        arguments::class.members.filterIsInstance<KMutableProperty<*>>()
            .forEach { argument ->
                val name = argument.name
                val stateArgument = checkNotNull(argumentCache.arguments[name])
                val oldValue = stateArgument.getter.call(state)
                val newValue = argument.getter.call(arguments)
                val changed = oldValue != newValue
                if (changed) {
                    stateArgument.setter.call(state, newValue)
                    changedArguments.add(name)
                    if (arguments.isArgumentForceNew(name)) {
                        forceNew = true
                    }
                }
            }

        return if (forceNew) {
            Action.Create(state)
        } else {
            Action.Update(state, changedArguments.toList())
        }
    }

    private fun <S: ResourceState, A: ResourceArguments> getMapper(stateClz: KClass<S>, argumentsClz: KClass<A>): ArgumentCache {
        return caches.computeIfAbsent(stateClz) {
            val stateProperties = stateClz.members.filterIsInstance<KMutableProperty<*>>()
                .map { it.name to it }
                .toMap()

            val arguments = argumentsClz.members.filterIsInstance<KMutableProperty<*>>()
                .map { it.name to checkNotNull(stateProperties[it.name]) }
                .toMap()

            ArgumentCache(arguments)
        }
    }
}
