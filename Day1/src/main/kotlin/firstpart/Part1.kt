package firstpart

import controller.CalibrationFIleController
import parsing.CalibrationFileParser
import java.io.File

class Part1 : CalibrationFIleController {

    var result = 0
        private set

    private val parser = CalibrationFileParser(this)
    private val calibrationValues: ArrayList<Int> = arrayListOf()

    override fun calculateResult() {
        result = calibrationValues.sum()
    }

    override fun parse(inputFile: File) {
        inputFile.forEachLine { line ->
            parser.findDigit(line).let { digit ->
                calibrationValues.add(digit)
            }
        }
    }

}
