import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day06Test {
    @Test
    fun `part1 provided example`() {
        val input = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()

        assertEquals(11, Day06().solvePart1(input))
    }

    @Test
    fun `part1 actual data`() {
        val input = Resources.read("06.txt")

        assertEquals(11, Day06().solvePart1(input))
    }
}