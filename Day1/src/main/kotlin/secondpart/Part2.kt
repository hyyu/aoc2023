package secondpart

import java.io.File

class Part2(inputFile: File) {

    var result = -1

    private val wordDigits: Map<String, Int> = mapOf(
        Pair("one", 1),
        Pair("two", 2),
        Pair("three", 3),
        Pair("four", 4),
        Pair("five", 5),
        Pair("six", 6),
        Pair("seven", 7),
        Pair("eight", 8),
        Pair("nine", 9),
    )

    init {
        result = solve(inputFile)
    }

    private fun solve(inputFile: File): Int {
        var sum = 0
        inputFile.forEachLine { line ->
            val a: Int? = line.firstDigitOrNull()
            val b: Int? = line.lastDigitOrNull()
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

    private fun String.firstDigitOrNull(): Int? {
        for (i in this.indices) {
            val c = this[i]
            if (c.isDigit())
                return c.intValue()

            wordDigits.forEach {
                val delta = i + it.key.length
                if (delta <= length) {
                    val substring = substring(i, delta)
                    if (substring == it.key) {
                        return it.value
                    }
                }
            }
        }
        return null
    }

    private fun String.lastDigitOrNull(): Int? {
        for (i in this.indices.reversed()) {
            val c = this[i]
            if (c.isDigit())
                return c.intValue()

            wordDigits.forEach {
                val delta = i + it.key.length
                if (delta <= length) {
                    if (substring(i, delta) == it.key)
                        return it.value
                }
            }
        }
        return null
    }

    private fun Char.intValue(): Int = toString().toInt()

}
