import java.io.File

class Part1(inputFile: File) {

    var result = -1

    init {
        result = solve(inputFile)
    }

    private fun solve(inputFile: File): Int {
        var sum = 0
        inputFile.forEachLine { line ->
            val a: Int? = line.firstDigitOrNull()?.intValue()
            val b: Int? = line.lastDigitOrNull()?.intValue()
            val calValue = when {
                a == null && b == null -> 0
                a == null -> (b?.times(10) ?: 0) + (b ?: 0)
                b == null -> a * 10 + a
                else -> a * 10 + b
            }
            sum += calValue
        }
        return sum
    }

    private fun String.firstDigitOrNull(): Char? = runCatching { first { it.isDigit() } }.getOrNull()
    private fun String.lastDigitOrNull(): Char? = runCatching { last { it.isDigit() } }.getOrNull()
    private fun Char.intValue(): Int = toString().toInt()

}
