package parsing

import controller.EngineController
import model.PartNumber

/**
 * Parser for an input in the format of Day 3 of AoC 2023
 *
 * @property controller The controller to call for custom use of the parsing
 *
 */
class EngineParser(
    private val controller: EngineController,
) {

    /**
     * Browse the given sequence to treat each window individually
     *
     * @param bufferSequence The given sequence of windows, each one  composed of 3 lines
     */
    fun browseBuffer(bufferSequence: Sequence<List<String>>) =
        bufferSequence.forEachIndexed { windowIndex, window ->
            evaluateWindow(windowIndex, window)
        }

    /**
     * Browse the given window to spot symbols
     *
     * @param windowIndex The index of the window in the parent sequence
     * @param window The given window
     */
    private fun evaluateWindow(
        windowIndex: Int,
        window: List<String>
    ) {
        for (i in window.indices) {
            for (j in window[i].indices) {
                if (!window[i][j].isRegular()) {
                    controller.treatSymbol(windowIndex, window, i, j)
                }
            }
        }
    }

    /**
     * Finds a part number from a given line and the index of one of its digits
     *
     * @param line The line of the neighbour
     * @param lineIndex The line index from the input file. To find this line index, the formula is:
     *
     * (the index of your window in the parent sequence) + (the index of the line in your window)
     * @param x The part number digit index in the line
     *
     * @return A [PartNumber] to detect duplicates later
     */
    fun retrievePartNumber(line: String, lineIndex: Int, x: Int): PartNumber {
        var i = x
        var j = x

        while (i < line.length && line[i].isDigit()) ++i
        while (j > 0 && line[j].isDigit()) --j

        i = i.takeIf { it < line.length } ?: line.length
        j = j.takeIf { line[it].isDigit() } ?: (j + 1)

        line.substring(j, i).toInt()
            .let { partNumberValue ->
                return PartNumber(
                    coordinates = lineIndex to j,
                    value = partNumberValue
                )
            }
    }

    /**
     * Applies given block to the given window's point neighbours
     *
     * @param window The given window
     * @param i The symbol ordinate in the window
     * @param j The symbol abscissa in the window
     */
    fun forEachNeighbour(
        window: List<String>,
        i: Int,
        j: Int,
        block: (Pair<Int, Int>) -> Unit
    ) {
        neighbours(window[i].length, i, j).forEach { coordinates ->
            block(coordinates)
        }
    }

    /**
     * Creates a list of pairs representing the 8 points around the given character.
     * The out-of-bounds coordinates are filtered out
     *
     * @param length The size of a line
     * @param y The character ordinate
     * @param x The character abscissa
     */
    private fun neighbours(length: Int, y: Int, x: Int): List<Pair<Int, Int>> =
        listOf(
            (y - 1) to (x - 1), (y - 1) to x,   (y - 1) to (x + 1),
            y to (x - 1),                       y to (x + 1),
            (y + 1) to (x - 1), (y + 1) to x,   (y + 1) to (x + 1)
        )
            .filter { (i, j) -> i in 0..< 3 && j in 0..< length }

    /**
     * Extension on [Char] to spot if the character is a symbol (anything but '.' and a digit)
     *
     * @return true if the character is regular (a '.' or a digit), false if the character is a symbol
     */
    private fun Char.isRegular() = isDigit() || this == '.'

}