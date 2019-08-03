package fuddle.engine

sealed class Request(val stateDir: String, val varFiles: List<String>) {
    class Import(stateDir: String, varFiles: List<String>): Request(stateDir, varFiles)
    class Plan(stateDir: String, varFiles: List<String>): Request(stateDir, varFiles)
    class Apply(stateDir: String, varFiles: List<String>): Request(stateDir, varFiles)
    class Destroy(stateDir: String, varFiles: List<String>): Request(stateDir, varFiles)
}
