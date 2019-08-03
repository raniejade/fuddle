package fuddle.engine

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.findObject
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required

object Cli: CliktCommand(printHelpOnEmptyArgs = true) {
    enum class Key {
        STATE_DIR,
        VAR_FILES,
        ENGINE
    }

    private val stateDir: String by option("--state-dir", help = "Directory to store state files")
        .required()

    private val varFiles: List<String> by option("--var-files", help = "List of files containing variables")
        .multiple()

    private val config by findObject { mutableMapOf<Key, Any?>() }

    override fun run() {
        config[Key.STATE_DIR] = stateDir
        config[Key.VAR_FILES] = varFiles
        config[Key.ENGINE] = EmbeddedEngine()
    }
}

abstract class Action(name: String, help: String): CliktCommand(name = name, help = help) {
    private val config by requireObject<Map<Cli.Key, Any?>>()

    @Suppress("UNCHECKED_CAST")
    protected fun <T> contextValue(key: Cli.Key) = lazy {
        config[key] as T
    }

    protected val stateDir: String by contextValue(Cli.Key.STATE_DIR)
    protected val varFiles: List<String> by contextValue(Cli.Key.VAR_FILES)
    protected val engine: Engine by contextValue(Cli.Key.ENGINE)

    object Import: Action("import", "Import a resource") {
        override fun run() {
        }
    }

    object Plan: Action("plan", "Show plan") {
        override fun run() {
        }
    }

    object Apply: Action("apply", "Apply plan") {
        override fun run() {
        }
    }

    object Destroy: Action("destroy", "Destroy orphaned resources") {
        override fun run() {
        }
    }

    companion object {
        val all = listOf(Import, Plan, Apply, Destroy)
    }
}


fun main(args: Array<String>) {
    Cli.subcommands(Action.all).main(args)
}
