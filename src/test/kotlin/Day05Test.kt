import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day05Test {
    @Test
    fun `boarding pass parsing`() {
        assertEquals(BoardingPass.parse("BFFFBBFRRR"), BoardingPass(70, 7))
        assertEquals(BoardingPass.parse("FFFBBBFRRR"), BoardingPass(14, 7))
        assertEquals(BoardingPass.parse("BBFFBBFRLL"), BoardingPass(102, 4))
    }

    @Test
    fun `part1 provided example`() {
        val input = """
            BFFFBBFRRR
            FFFBBBFRRR
            BBFFBBFRLL
        """.trimIndent()

        assertEquals(820, Day05().solvePart1(input))
    }

    @Test
    fun `part1 actual data`() {
        val input = Resources.read("05.txt")

        assertEquals(850, Day05().solvePart1(input))
    }

    @Test
    fun part2() {
        val input = Resources.read("05.txt")

        assertEquals(599, Day05().solvePart2(input))
    }
}