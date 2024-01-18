package secondpart

import parsing.EngineParser
import controller.EngineController
import model.PartNumber
import java.io.File

/**
 * Class for Part 2 of Day 3 of AoC 2023
 *
 * @property result The solution to check from outside the class
 * @property parser The parser for Day 3 of AoC 2023, check [EngineParser]
 * @property gears The list containing the gears without duplicates
 */
class Part2 : EngineController {

    var result = 0

    private val parser = EngineParser(this)
    private val gears: ArrayList<Pair<PartNumber, PartNumber>> = arrayListOf()

    /**
     * Calculates the result in the context of Part 1
     */
    override fun calculateResult() {
        result = gears.sumOf { it.first.value * it.second.value }
    }

    /**
     * Launches the input file's parsing
     *
     * Check the super method for parameters information: [EngineController.parse]
     */
    override fun parse(inputFile: File) =
        inputFile.useLines { initialSequence ->
            parser.browseBuffer(
                initialSequence.windowed(
                    size = 3,
                    step = 1
                )
            )
        }

    /**
     * Finds gears from the given symbol coordinates inside the given window
     *
     * Check the super method for parameters information: [EngineController.treatSymbol]
     */
    override fun treatSymbol(windowIndex: Int, window: List<String>, i: Int, j: Int) {
        findGear(windowIndex, window, i, j)
            .takeIf { !gears.contains(it) }
            ?.let { gears.add(it) }
    }

    /**
     * Finds out if the symbol found in the window is a gear
     *
     * @param windowIndex: The given window's parent index
     * @param window: The given window
     * @param i: The symbol ordinate in the window
     * @param j: The symbol abscissa in the window
     *
     * @return: A pair representing the gear with its 2 adjacent part numbers
     */
    private fun findGear(
        windowIndex: Int,
        window: List<String>,
        i: Int,
        j: Int
    ): Pair<PartNumber, PartNumber>? {
        val partNumbers: ArrayList<PartNumber> = arrayListOf()

        parser.forEachNeighbour(window, i, j) { (y, x) ->
            if (window[y][x].isDigit()) {
                parser.retrievePartNumber(
                    line = window[y],
                    lineIndex = windowIndex + y,
                    x = x
                )
                    .takeIf { !partNumbers.contains(it) }
                    ?.let { partNumbers.add(it) }
            }
        }

        return partNumbers.takeIf { it.size == 2 }
            ?.let { it[0] to it[1] }
    }

}
