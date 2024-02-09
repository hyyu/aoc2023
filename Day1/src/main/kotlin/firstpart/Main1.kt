package firstpart

import java.io.File

fun main(args: Array<String>) {
    start()

    args.ifEmpty { return finish() }

    val filename = args[0]
    val file = File(filename)

    println("File: $filename")
    println()

    Part1().let { solver ->
        solver.parse(file)

        println("Calibration values sum = ${solver.calculateResult()}")
        println()
    }

    finish()
}

private fun start() {
    println("START")
    println()
}

private fun finish() {
    println("END")
}
