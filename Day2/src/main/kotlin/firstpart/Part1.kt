package firstpart

import firstpart.controller.GameValidRunsController
import firstpart.parsing.GameValidRunsParser
import java.io.File

class Part1 : GameValidRunsController {

    var result = 0

    private val colorMaxCounts = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    private val parser = GameValidRunsParser(this)
    private val validGameRuns: ArrayList<Int> = arrayListOf()

    override fun calculateResult() {
        result = validGameRuns.sum()
    }

    override fun parse(inputFile: File) {
        inputFile.forEachLine { line ->
            parser.parseGameData(line).let { (gameId, gameTurns) ->
                parser.evaluateGameRun(gameId, gameTurns)?.let { validGameRuns.add(it) }
            }
        }
    }

    override fun isTurnValid(colorCounts: List<String>): Boolean =
        colorCounts.any { colorQuantityString ->
            parser.parseColor(colorQuantityString).let { (colorValue, colorName) ->
                !colorMaxCounts.any { colorName == it.key && colorValue > it.value }
            }
        }

}
