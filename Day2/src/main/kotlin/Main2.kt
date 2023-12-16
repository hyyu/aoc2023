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

    val solver = Part2(file)

    println("Products sum = ${solver.result}")
    println()

    println("END")
}
