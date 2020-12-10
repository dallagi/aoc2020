import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun `part1 first provided example`() {
        val input = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()

        assertEquals(7 * 5, Day10().solvePart1(input))
    }

    @Test
    fun `part1 second provided example`() {
        val input = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()

        assertEquals(22 * 10, Day10().solvePart1(input))
    }

    @Test
    fun `part1 actual data`() {
        val input = Resources.read("10.txt")

        assertEquals(2263, Day10().solvePart1(input))
    }

    @Test
    fun `part2 first provided example`() {
        val input = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()

        assertEquals(8, Day10().solvePart2(input))
    }

    @Test
    fun `part2 second provided example`() {
        val input = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()

        assertEquals(19208, Day10().solvePart2(input))
    }

    @Test
    fun `part2 actual input`() {
        val input = Resources.read("10.txt")

        assertEquals(396857386627072, Day10().solvePart2(input))
    }
}