package controller

import java.io.File

interface CalibrationFIleController {

    /**
     * Override to implement result's calculation
     */
    fun calculateResult()

    /**
     * Override to implement the launch of input parsing
     *
     * @param inputFile The file to parse
     */
    fun parse(inputFile: File)

}
