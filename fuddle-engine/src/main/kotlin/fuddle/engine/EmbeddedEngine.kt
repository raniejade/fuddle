package fuddle.engine

import fuddle.engine.context.ContextFactory
import fuddle.engine.context.ContextImpl
import fuddle.engine.util.logger

class EmbeddedEngine: Engine {
    override fun execute(request: Request) {
        logger.info { "Using state directory: ${request.stateDir}" }
        if (request.varFiles.isNotEmpty()) {
            logger.info { "Loading variables from: ${request.varFiles}" }
        }
    }

    private fun createContext(): ContextImpl {
        return ContextFactory.create()
    }
}
