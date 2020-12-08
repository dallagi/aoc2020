import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day08Test {
    @Test
    fun `part1 provided example`() {
        val input = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()

        assertEquals(5, Day08().solvePart1(input))
    }

    @Test
    fun `part1 actual data`() {
        val input = Resources.read("08.txt")

        assertEquals(1528, Day08().solvePart1(input))
    }

    @Test
    fun `part2 provided example`() {
        val input = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()

        assertEquals(8, Day08().solvePart2(input))
    }

    @Test
    fun `part2 actual data`() {
        val input = Resources.read("08.txt")

        assertEquals(640, Day08().solvePart2(input))
    }
}