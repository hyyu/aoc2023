package secondpart

import controller.CalibrationFileController
import parsing.CalibrationFileParser
import java.io.File

class Part2 : CalibrationFileController {

    var result = -1
    private set

    private val parser = CalibrationFileParser(this)
    private val calibrationValues: ArrayList<Int> = arrayListOf()

    override fun calculateResult() {
        result = calibrationValues.sum()
    }

    override fun parse(inputFile: File) {
        inputFile.forEachLine { line ->
            parser.findCalibrationValue(line).let { calibrationValues.add(it) }
        }
    }

    override fun findFirstDigit(line: String): Int? {
        for (i in line.indices) {
            line[i].takeIf { it.isDigit() }
                ?.let { return it.digitToInt() }

            parser.findDigitInDictionary(i, line)?.let { return it }
        }

        return null
    }

    override fun findLastDigit(line: String): Int? {
        for (i in line.indices.reversed()) {
            line[i].takeIf { it.isDigit() }
                ?.let { return it.digitToInt() }

            parser.findDigitInDictionary(i, line)?.let { return it }
        }

        return null
    }

}
