package parsing

import controller.CalibrationFIleController

class CalibrationFileParser(private val controller: CalibrationFIleController) {

    fun findDigit(line: String): Int =
        (line.firstDigitOrNull() to line.lastDigitOrNull()).let { digitPair ->
            buildDigit(digitPair)
        }

    private fun buildDigit(digitPair: Pair<Int?, Int?>) = digitPair.let { (a, b) ->
        when {
            a == null && b == null -> 0
            a == null -> (b?.times(10) ?: 0) + (b ?: 0)
            b == null -> a * 10 + a
            else -> a * 10 + b
        }
    }

    private fun String.firstDigitOrNull(): Int? = runCatching { first { it.isDigit() } }.getOrNull()?.digitToInt()
    private fun String.lastDigitOrNull(): Int? = runCatching { last { it.isDigit() } }.getOrNull()?.digitToInt()

}