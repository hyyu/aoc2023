import java.io.File

class Part1(inputFile: File) {

    var result = 0

    private val colors = mapOf(
        Pair("red", 12),
        Pair("green", 13),
        Pair("blue", 14)
    )

    init {
        solve(inputFile)
    }

    private fun solve(inputFile: File) {
        inputFile.forEachLine { line ->
            splitLineIdFromContent(line).let { splitLine ->
                result += getLineResult(splitLine)
            }
        }
    }

    private fun getLineResult(splitLine: List<String>): Int =
        getGameIdFromLine(splitLine[0]).let { gameId ->
            parseLine(splitLine[1]).forEach { cubeSet ->
                splitCubes(cubeSet).let { cubes ->
                    if (hasError(cubes))
                        return 0
                }
            }
            return gameId
        }

    private fun hasError(cubes: List<String>): Boolean =
        cubes.any { colorQuantityString ->
            colorQuantityString.trim().split(" ").let { splitColor ->
                !isValueValid(
                    colorName = splitColor[1],
                    colorValue = splitColor[0].toInt()
                )
            }
        }

    private fun isValueValid(colorName: String, colorValue: Int): Boolean {
        colors.forEach { entry ->
            if (colorName == entry.key && colorValue > entry.value)
                return false
        }
        return true
    }

    private fun splitLineIdFromContent(line: String): List<String> = line.split(":")
    private fun getGameIdFromLine(line: String): Int = line.trim().split(" ")[1].toInt()
    private fun parseLine(splitLine: String): List<String> = splitLine.trim().split(";")
    private fun splitCubes(cubeSet: String): List<String> = cubeSet.trim().split(",")

}
