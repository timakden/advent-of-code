package ru.timakden.aoc

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.engine.concurrency.TestExecutionMode

object Config : AbstractProjectConfig() {
    override val testExecutionMode = TestExecutionMode.LimitedConcurrency(5)
}
