package fuddle.script

import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
    displayName = "Fuddle",
    fileExtension = "fd.kts",
    compilationConfiguration = ScriptConfiguration::class
)
abstract class ScriptDefinition
