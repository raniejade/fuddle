package fuddle.engine

import fuddle.engine.context.ContextFactory
import fuddle.engine.util.logger

class EmbeddedEngine: Engine {
    private val context by lazy { ContextFactory.create() }

    override fun execute(request: Request) {
        logger.info { "Using state directory: ${request.stateDir}" }
        if (request.varFiles.isNotEmpty()) {
            logger.info { "Loading variables from: ${request.varFiles}" }
        }
        context
    }
}
