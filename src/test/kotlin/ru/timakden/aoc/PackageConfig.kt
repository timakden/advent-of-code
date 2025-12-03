package ru.timakden.aoc

import io.kotest.core.config.AbstractPackageConfig
import io.kotest.engine.concurrency.TestExecutionMode

class PackageConfig : AbstractPackageConfig() {
    override val failfast = true
    override val testExecutionMode = TestExecutionMode.Concurrent
}
