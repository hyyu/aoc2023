package firstpart.controller

import java.io.File

interface GameValidRunsController {

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
     * Override to implement the validation for a turn
     *
     * @param colorCounts The turn's color counts
     *
     * @return true if the turn is invalid, false otherwise
     */
    fun isTurnWrong(colorCounts: List<String>): Boolean

}
