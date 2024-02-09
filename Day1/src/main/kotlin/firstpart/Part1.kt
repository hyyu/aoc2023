package firstpart

import controller.CalibrationFileController
import parsing.CalibrationFileParser
import java.io.File

class Part1 : CalibrationFileController {

    private val parser = CalibrationFileParser(this)
    private val calibrationValues: ArrayList<Int> = arrayListOf()

    override fun calculateResult(): Int = calibrationValues.sum()

    override fun parse(inputFile: File) {
        inputFile.forEachLine { line ->
            parser.findCalibrationValue(line).let { calibrationValues.add(it) }
        }
    }

    override fun findFirstDigit(line: String): Int? =
        runCatching { line.first { it.isDigit() } }.getOrNull()?.digitToInt()

    override fun findLastDigit(line: String): Int? =
        runCatching { line.last { it.isDigit() } }.getOrNull()?.digitToInt()

}
