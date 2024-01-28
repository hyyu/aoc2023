package secondpart.parsing

import secondpart.controller.GameMinValuesController
import secondpart.model.ColorMaxQuantityHolder

class GameMinValuesParser(private val controller: GameMinValuesController) {

    companion object {
        private const val QUANTITIES_REGEX_PATTERN = "\\d+ ((red)|(green)|(blue))"
    }

    private val quantitiesRegex = QUANTITIES_REGEX_PATTERN.toRegex()

    fun parseGameData(line: String): List<String> = splitTurns(line.split(":")[1].trim())

    fun evaluateGameRun(gameRun: List<String>): ColorMaxQuantityHolder =
        ColorMaxQuantityHolder().also { holder ->
            gameRun.forEach { run ->
                getCubeQuantities(run).forEach { colorQuantity ->
                    controller.treatColorQuantity(colorQuantity, holder)
                }
            }
        }

    fun parseColor(colorQuantityString: String): Pair<Int, String> =
        splitQuantity(colorQuantityString).let {
            it[0].toInt() to it[1]
        }

    private fun splitTurns(gameRun: String): List<String> = gameRun.split(";")
    private fun getCubeQuantities(line: String): Sequence<String> =
        quantitiesRegex.findAll(line).map { it.groupValues[0] }
    private fun splitQuantity(quantity: String): List<String> = quantity.split(" ")

}
