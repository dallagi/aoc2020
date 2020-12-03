// Solves Day 3: Toboggan Trajectory https://adventofcode.com/2020/day/3
class Day03 {
    fun solvePart1(input: String): Int {
        val grid = Grid.fromInput(input)

        return treesforSlope(grid, 3, 1)
    }

    fun solvePart2(input: String): Long {
        val grid = Grid.fromInput(input)
        val slopesToCheck = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))

        return productOfTreesForSlopes(grid, slopesToCheck)
    }

    private fun productOfTreesForSlopes(grid: Grid, slopesToCheck: List<Pair<Int, Int>>): Long {
        return slopesToCheck
            .map { (xDelta, yDelta) -> treesforSlope(grid, xDelta, yDelta).toLong() }
            .reduce { acc, i -> acc * i }
    }

    private fun treesforSlope(grid: Grid, xDelta: Int, yDelta: Int): Int {
        var x = 0
        var y = 0
        var treesCount = 0
        while (y < grid.length) {
            if (grid.treeAt(x, y)) treesCount++
            x += xDelta
            y += yDelta
        }
        return treesCount
    }
}

class Grid private constructor(private val trees: List<List<Boolean>>) {
    val length: Int
            get() = trees.size

    fun treeAt(x: Int, y: Int): Boolean {
        val row = trees[y]
        return row[x % row.size]
    }

    companion object {
        private const val TREE_CHAR = '#'

        fun fromInput(input: String): Grid {
            val trees = input
                .split("\n")
                .map { row -> row.map { char -> char == TREE_CHAR } }
            return Grid(trees)
        }
    }
}
