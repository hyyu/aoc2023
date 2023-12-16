import java.io.File

class Part2(inputFile: File) {

    var result = 0

    private val quantitiesRegex = Regex(QUANTITIES_REGEX_PATTERN)
    private var colorMaxQuantities = ColorMaxQuantityHolder()

    init {
        solve(inputFile)
    }

    private fun solve(inputFile: File) {
        inputFile.forEachLine { line ->
            colorMaxQuantities = ColorMaxQuantityHolder()
            result += productFromLine(line)
        }
    }

    private fun productFromLine(line: String): Int = splitLineIdFromContent(line).let { splitLine ->
        getCubeQuantities(splitLine[1]).forEach { colorQuantityString ->
            splitQuantity(colorQuantityString).let { quantity ->
                Pair(quantity[0].toInt(), quantity[1])
                    .let { (colorValue, colorName) -> colorMaxQuantities.setProps(colorValue, colorName) }
            }
        }
        return colorMaxQuantities.product()
    }

    private fun splitLineIdFromContent(line: String): List<String> = line.split(":")
    private fun splitQuantity(quantity: String): List<String> = quantity.split(" ")
    private fun getCubeQuantities(line: String): List<String> =
        quantitiesRegex.findAll(line).map { it.groupValues[0] }.toList()

    private data class ColorMaxQuantityHolder(
        var redCubes: Int = 0,
        var greenCubes: Int = 0,
        var blueCubes: Int = 0
    ) {
        fun setProps(colorValue: Int, colorName: String) {
            when (colorName) {
                RED_LABEL -> colorValue.takeIf { it > redCubes }
                    ?.let { redCubes = it }

                GREEN_LABEL -> colorValue.takeIf { it > greenCubes }
                    ?.let { greenCubes = it }

                BLUE_LABEL -> colorValue.takeIf { it > blueCubes }
                    ?.let { blueCubes = it }
            }
        }
        fun product() = redCubes * greenCubes * blueCubes
    }

    companion object {
        private const val QUANTITIES_REGEX_PATTERN = "\\d+ ((red)|(green)|(blue))"
        private const val RED_LABEL = "red"
        private const val GREEN_LABEL = "green"
        private const val BLUE_LABEL = "blue"
    }

}
