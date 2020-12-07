class Day07 {
    fun solvePart1(input: String): Int {
        val bagsGraph = BagsGraph.parse(input)

        return bagsGraph.countOfBagsThatContain("shiny gold")
    }

}

private class BagsGraph private constructor(private val relations: Map<String, MutableList<Pair<Int, String>>>) {
    fun countOfBagsThatContain(targetBagColor: String) =
        relations.keys.count { containerBagColor ->
            doesContain(containerBagColor, targetBagColor)
        }

    private fun doesContain(containerBagColor: String, targetBagColor: String): Boolean {
        return (targetBagColor in bagColorsContainedIn(containerBagColor))
                || bagColorsContainedIn(containerBagColor).any { doesContain(it, targetBagColor) }
    }

    private fun bagColorsContainedIn(containerBagColor: String) = relations[containerBagColor]!!.map { it.second }

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
