package fuddle.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import fuddle.engine.EmbeddedEngine
import fuddle.engine.Engine

object Cli: CliktCommand(printHelpOnEmptyArgs = true) {
    override fun run() = Unit
}

abstract class Action(name: String, help: String): CliktCommand(name = name, help = help) {

    protected val stateDir: String by option("-s", "--state-dir", help = "Directory to store state files")
        .required()

    protected val varFiles: List<String> by option("-f", "--var-files", help = "List of files containing variables")
        .multiple()

    protected val engine: Engine by lazy { EmbeddedEngine() }

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
