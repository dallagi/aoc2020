import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day09Test {
    @Test
    fun `part1 provided example`() {
        val input = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()

        assertEquals(127, Day09().solvePart1(input, preambleSize = 5))
    }

    @Test
    fun `part1 actual data`() {
        val input = Resources.read("09.txt")

        assertEquals(393911906, Day09().solvePart1(input, preambleSize = 25))
    }

    @Test
    fun `part2 provided example`() {
        val input = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()

        assertEquals(62, Day09().solvePart2(input, preambleSize = 5))
    }

    @Test
    fun `part2 actual data`() {
        val input = Resources.read("09.txt")

        assertEquals(59341885, Day09().solvePart2(input, preambleSize = 25))
    }
}