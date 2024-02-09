package firstpart

import parsing.EngineParser
import controller.EngineController
import model.PartNumber
import java.io.File

/**
 * Class for Part 1 of Day 3 of AoC 2023
 *
 * @property result The solution to check from outside the class
 * @property parser The parser for Day 3 of AoC 2023, check [EngineParser]
 * @property partNumbers The list containing the part numbers without duplicates
 */
class Part1 : EngineController {

    var result = 0
        private set

    private val parser = EngineParser(this)
    private val partNumbers: ArrayList<PartNumber> = arrayListOf()

    /**
     * Calculates the result in the context of Part 1
     */
    override fun calculateResult() = partNumbers.sumOf { it.value }

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
     * Finds part numbers from the given symbol coordinates inside the given window
     *
     * Check the super method for parameters information: [EngineController.treatSymbol]
     */
    override fun treatSymbol(
        windowIndex: Int,
        window: List<String>,
        i: Int,
        j: Int
    ) = parser.forEachNeighbour(window, i, j) { (y, x) ->
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

}
