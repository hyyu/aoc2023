package controller

import java.io.File

/**
 * Contract to control the [parsing.EngineParser] content
 */
interface EngineController {

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
     * Override to manage the given symbol on child class side
     *
     * @param windowIndex The given window's parent index
     * @param window The given window
     * @param i The symbol ordinate in the window
     * @param j The symbol abscissa in the window
     */
    fun treatSymbol(windowIndex: Int, window: List<String>, i: Int, j: Int)

}
