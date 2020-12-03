class Day03 {
    fun solvePart1(input: String): Int {
        val grid = Grid.fromInput(input)

        var x = 0
        var y = 0
        var treesCount = 0
        while(y < grid.length) {
            if(grid.treeAt(x, y)) treesCount++
            x += 3
            y += 1
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
