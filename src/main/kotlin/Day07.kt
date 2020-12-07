class Day07 {
    fun solvePart1(input: String): Int {
        val bagsGraph = BagsGraph.parse(input)

        return bagsGraph.countOfBagsThatContain("shiny gold")
    }

    fun solvePart2(input: String): Int {
        val bagsGraph = BagsGraph.parse(input)

        return bagsGraph.necessaryBagsFor("shiny gold") - 1
    }

}

private class BagsGraph private constructor(private val relations: Map<String, MutableList<Pair<Int, String>>>) {
    fun countOfBagsThatContain(target: String) =
        relations.keys.count { container ->
            doesContain(container, target)
        }

    fun necessaryBagsFor(targetBagColor: String): Int {
        val contained = relations[targetBagColor]!!

        return 1 + contained.sumOf { it.first * necessaryBagsFor(it.second) }
    }

    private fun doesContain(container: String, target: String): Boolean {
        return (target in bagsContainedIn(container)) || bagsContainedIn(container).any { doesContain(it, target) }
    }

    private fun bagsContainedIn(containerBagColor: String) = relations[containerBagColor]!!.map { it.second }

    companion object {
        fun parse(input: String): BagsGraph {
            val relations = mutableMapOf<String, MutableList<Pair<Int, String>>>()

            val rows = input.split("\n")
            for (row in rows) {
                val (container, allContained) = row.removeSuffix(".").split(" contain ", limit = 2)

                val containerColor = container.removeSuffix(" bags")
                relations[containerColor] = mutableListOf()

                for (contained in allContained.split(", ")) {
                    if (contained == "no other bags") break

                    val regex = Regex("""(\d+) (.+) bags?""")
                    val (containedCount, containedColor) = regex.find(contained)!!.destructured
                    relations[containerColor]!!.add(containedCount.toInt() to containedColor)
                }
            }
            return BagsGraph(relations)
        }
    }
}
