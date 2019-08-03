package fuddle.engine

import fuddle.engine.context.ContextFactory
import fuddle.engine.context.ContextImpl

class EmbeddedEngine: Engine {
    private fun createContext(): ContextImpl {
        return ContextFactory.create()
    }
}
