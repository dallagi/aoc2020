import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun `part1 provided example`() {
        val input = """
            1721
            979
            366
            299
            675
            1456
        """.trimIndent()

        assertEquals(514579, Day01().solvePart1(input))
    }

    @Test
    fun `part1 actual data`() {
        val input = Resources.read("01.txt")

        assertEquals(840324, Day01().solvePart1(input))
    }

    @Test
    fun `part2 provided example`() {
        val input = """
            1721
            979
            366
            299
            675
            1456
        """.trimIndent()

        assertEquals(241861950, Day01().solvePart2(input))
    }

    @Test
    fun `part2 actual data`() {
        val input = Resources.read("01.txt")

        assertEquals(170098110, Day01().solvePart2(input))
    }
}