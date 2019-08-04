package fuddle.cli

import ch.qos.logback.classic.util.ContextInitializer
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import fuddle.engine.EmbeddedEngine
import fuddle.engine.Engine
import fuddle.engine.Request
import java.nio.file.Paths

object Cli: CliktCommand(printHelpOnEmptyArgs = true) {
    override fun run() = Unit
}

abstract class Action(name: String, help: String): CliktCommand(name = name, help = help) {

    private val stateDir: String by option("-s", "--state-dir", help = "Directory to store state files")
        .required()

    private val varFiles: List<String> by option("-f", "--var-files", help = "List of files containing variables")
        .multiple()

    private val debug by option("--debug").flag()

    private val engine: Engine by lazy { EmbeddedEngine() }

    private val currentWorkingDirectory by lazy {
        Paths.get(System.getProperty("user.dir"))
    }

    abstract fun request(stateDir: String, varFiles: List<String>): Request

    override fun run() {
        initLogging()
        val resolvedStateDir = resolvePath(stateDir)
        val resolvedVarFiles = varFiles.map(this::resolvePath)
        engine.execute(request(resolvedStateDir, resolvedVarFiles))
    }

    object Import: Action("import", "Import a resource") {
        override fun request(stateDir: String, varFiles: List<String>): Request {
            return Request.Import(stateDir, varFiles)
        }
    }

    object Plan: Action("plan", "Show plan") {
        override fun request(stateDir: String, varFiles: List<String>): Request {
            return Request.Plan(stateDir, varFiles)
        }
    }

    object Apply: Action("apply", "Apply plan") {
        override fun request(stateDir: String, varFiles: List<String>): Request {
            return Request.Apply(stateDir, varFiles)
        }
    }

    object Destroy: Action("destroy", "Destroy orphaned resources") {
        override fun request(stateDir: String, varFiles: List<String>): Request {
            return Request.Destroy(stateDir, varFiles)
        }
    }

    private fun resolvePath(path: String): String {
        val p = Paths.get(path)

        return if (p.isAbsolute) {
            path
        } else {
            currentWorkingDirectory.resolve(path).toString()
        }
    }

    private fun initLogging() {
        val config = if (debug) {
            "logback-debug.xml"
        } else {
            "logback.xml"
        }

        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, config)
    }

    companion object {
        const val USE_DAEMON_BY_DEFAULT = false
        val all = listOf(Import, Plan, Apply, Destroy)
    }
}


fun main(args: Array<String>) {
    Cli.subcommands(Action.all).main(args)
}
