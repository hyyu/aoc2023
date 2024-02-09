package secondpart

import secondpart.controller.GameMinValuesController
import secondpart.model.ColorMaxQuantityHolder
import secondpart.parsing.GameMinValuesParser
import java.io.File

class Part2 : GameMinValuesController {

    private val parser = GameMinValuesParser(this)
    private val productsList: ArrayList<Int> = arrayListOf()

    override fun calculateResult(): Int = productsList.sum()

    override fun parse(inputFile: File) {
        inputFile.forEachLine { line ->
            parser.parseGameData(line).let { gameRun ->
                parser.evaluateGameRun(gameRun).let {
                    productsList.add(it.product())
                }
            }
        }
    }

    override fun treatColorQuantity(colorQuantity: String, holder: ColorMaxQuantityHolder) {
        parser.parseColor(colorQuantity).let { (colorValue, colorName) ->
            holder.treatProperty(colorValue, colorName)
        }
    }

}
