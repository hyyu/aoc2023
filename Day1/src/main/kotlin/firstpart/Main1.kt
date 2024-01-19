package firstpart

import java.io.File

fun main(args: Array<String>) {
    println("START")
    println()

    if (args.isEmpty())
        return

    val filename = args[0]
    val file = File(filename)

    println("File: $filename")
    println()

    Part1().let { solver ->
        solver.parse(file)
        solver.calculateResult()

        println("Calibration values sum = ${solver.result}")
        println()
    }


    println("END")
}
