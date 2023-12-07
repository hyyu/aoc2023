import java.io.File

fun main(args: Array<String>) {
    println("START")
    println()

    if (args.isEmpty())
        return

    val filename = args[0]
    val file = File(filename)

    val solver = Part2(file)

    println("Calibration values sum = ${solver.result}")
    println()

    println("END")
}
