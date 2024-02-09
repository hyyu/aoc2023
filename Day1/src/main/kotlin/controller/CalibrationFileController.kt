package controller

import java.io.File

interface CalibrationFileController {

    /**
     * Override to implement result's calculation
     */
    fun calculateResult(): Int

    /**
     * Override to implement the launch of input parsing
     *
     * @param inputFile The file to parse
     */
    fun parse(inputFile: File)

    /**
     * Override to implement how to find the first digit
     *
     * @param line The line to browse
     *
     * @return The digit if found, null otherwise
     */
    fun findFirstDigit(line: String): Int?

    /**
     * Override to implement how to find the last digit
     *
     * @param line The line to browse
     *
     * @return The digit if found, null otherwise
     */
    fun findLastDigit(line: String): Int?

}
