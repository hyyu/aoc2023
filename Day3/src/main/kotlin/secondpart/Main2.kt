package secondpart

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

    Part2().let { solver ->
        solver.parse(file)

        println("Gear ratios sum = ${solver.calculateResult()}")
        println()
    }


    println("END")
}
