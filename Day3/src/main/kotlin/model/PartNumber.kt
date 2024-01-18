package model

/**
 * Data class holding a part number value and its coordinates
 *
 * @property coordinates: The coordinates of the part number's left digit
 * @property value: The part number's value
 */
data class PartNumber(
    val coordinates: Pair<Int, Int>,
    val value: Int
)
