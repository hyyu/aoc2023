package secondpart.model

data class ColorMaxQuantityHolder(
    var redCubes: Int = 0,
    var greenCubes: Int = 0,
    var blueCubes: Int = 0
) {

    companion object {
        private const val RED_LABEL = "red"
        private const val GREEN_LABEL = "green"
        private const val BLUE_LABEL = "blue"
    }

    fun treatProperty(colorValue: Int, colorName: String) {
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
