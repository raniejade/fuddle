package fuddle.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.groups.mutuallyExclusiveOptions
import com.github.ajalt.clikt.parameters.options.*
import fuddle.engine.EmbeddedEngine
import fuddle.engine.Engine
import fuddle.server.RemoteEngine

object Cli: CliktCommand(printHelpOnEmptyArgs = true) {
    override fun run() = Unit
}

abstract class Action(name: String, help: String): CliktCommand(name = name, help = help) {

    protected val stateDir: String by option("-s", "--state-dir", help = "Directory to store state files")
        .required()

    protected val varFiles: List<String> by option("-f", "--var-files", help = "List of files containing variables")
        .multiple()

    private val daemon: Boolean? by option("--daemon", envvar = "FUDDLE_DAEMON").flag("--no-daemon", default = USE_DAEMON_BY_DEFAULT)

    protected val engine: Engine by lazy {
        // check properties first
        var useDaemon = System.getProperty("dev.fuddle.daemon")?.toBoolean() ?: USE_DAEMON_BY_DEFAULT

        // cli flags override properties
        daemon?.let { it ->
            useDaemon = it
        }

        if (useDaemon) {
            RemoteEngine()
        } else {
            EmbeddedEngine()
        }
    }

    object Import: Action("import", "Import a resource") {
        override fun run() {
            println(engine::class)
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
        val USE_DAEMON_BY_DEFAULT = false
        val all = listOf(Import, Plan, Apply, Destroy)
    }
}


fun main(args: Array<String>) {
    Cli.subcommands(Action.all).main(args)
}
