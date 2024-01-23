package parsing

import controller.CalibrationFileController

class CalibrationFileParser(private val controller: CalibrationFileController) {

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

    fun findCalibrationValue(line: String): Int =
        buildCalibrationValue(
            (controller.findFirstDigit(line) to controller.findLastDigit(line))
        )

    fun findDigitInDictionary(i: Int, line: String, ): Int? {
        wordDigits.forEach { entry ->
            (i + entry.key.length).takeIf { it <= line.length }
                ?.let { delta ->
                    line.substring(i, delta)
                        .takeIf { it == entry.key }
                        ?.let { return entry.value }
                }
        }

        return null
    }

    private fun buildCalibrationValue(digitPair: Pair<Int?, Int?>): Int = digitPair.let { (a, b) ->
        when {
            a == null && b == null -> 0
            a == null -> (b?.times(10) ?: 0) + (b ?: 0)
            b == null -> a * 10 + a
            else -> a * 10 + b
        }
    }

}
