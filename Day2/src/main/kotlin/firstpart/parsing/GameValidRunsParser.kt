package firstpart.parsing

import firstpart.controller.GameValidRunsController

class GameValidRunsParser(private val controller: GameValidRunsController) {

    fun parseGameData(line: String): Pair<Int, List<String>> = line.split(":").let {
        getGameIdFromChunk(it[0].trim()) to splitTurns(it[1].trim())
    }

    fun evaluateGameRun(gameId: Int, gameRun: List<String>): Int? {
        gameRun.forEach { cubeSet ->
            splitColors(cubeSet).let { cubes ->
                cubes.takeIf { controller.isTurnWrong(it) }
                    ?.let { return null }
            }
        }
        return gameId
    }

    fun parseColor(colorQuantityString: String): Pair<Int, String> =
        splitCount(colorQuantityString).let {
            it[0].toInt() to it[1]
        }

    private fun getGameIdFromChunk(chunk: String): Int = chunk.split(" ")[1].toInt()
    private fun splitTurns(gameRun: String): List<String> = gameRun.split(";")
    private fun splitColors(cubeSet: String): List<String> = cubeSet.trim().split(",")
    private fun splitCount(colorQuantityString: String) = colorQuantityString.trim().split(" ")

}
