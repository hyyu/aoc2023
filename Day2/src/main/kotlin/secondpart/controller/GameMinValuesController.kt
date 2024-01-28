package secondpart.controller

import secondpart.model.ColorMaxQuantityHolder
import java.io.File

interface GameMinValuesController {

    /**
     * Override to implement result's calculation
     */
    fun calculateResult()

    /**
     * Override to implement the launch of input parsing
     *
     * @param inputFile The file to parse
     */
    fun parse(inputFile: File)

    /**
     * Override to implement the treatment for a cube quantity from a game run
     *
     * @param colorQuantity : A color as string
     * @param holder: The holder to retain minimum color values. Check [ColorMaxQuantityHolder]
     */
    fun treatColorQuantity(colorQuantity: String, holder: ColorMaxQuantityHolder)

}
