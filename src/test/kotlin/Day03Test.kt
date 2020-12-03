import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun `part1 provided example`() {
        val input = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
       """.trimIndent()

        assertEquals(7, Day03().solvePart1(input))
    }

    @Test
    fun `part1 actual data`() {
        val input = Resources.read("03.txt")

        assertEquals(187, Day03().solvePart1(input))
    }
}