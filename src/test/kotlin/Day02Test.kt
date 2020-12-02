import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day02Test {
    @Test
    fun `part1 provided example`() {
        val input = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent()

        assertEquals(2, Day02().solvePart1(input))
    }

    @Test
    fun `part1 actual data`() {
        val input = Resources.read("02.txt")

        assertEquals(493, Day02().solvePart1(input))
    }

    @Test
    fun `part2 example data`() {
        val input = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent()

        assertEquals(1, Day02().solvePart2(input))
    }

    @Test
    fun `part2 actual data`() {
        val input = Resources.read("02.txt")

        assertEquals(593, Day02().solvePart2(input))
    }
}