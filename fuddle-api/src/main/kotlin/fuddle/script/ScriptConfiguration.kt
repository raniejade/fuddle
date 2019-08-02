package fuddle.script

import fuddle.Context
import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.api.defaultImports
import kotlin.script.experimental.api.implicitReceivers

class ScriptConfiguration: ScriptCompilationConfiguration({
    defaultImports("fuddle.*")
    implicitReceivers(Context::class)
})
