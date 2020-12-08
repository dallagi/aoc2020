class Day07 {
    fun solvePart1(input: String) = BagsGraph.parse(input).countOfBagsThatContain("shiny gold")

    fun solvePart2(input: String) = BagsGraph.parse(input).necessaryBagsFor("shiny gold") - 1
}

private data class Bags(val amount: Int, val color: String)

private class BagsGraph private constructor(private val relations: Map<String, MutableList<Bags>>) {
    fun countOfBagsThatContain(target: String) =
        relations.keys.count { container ->
            doesContain(container, target)
        }

    fun necessaryBagsFor(targetBagColor: String): Int {
        val contained = relations[targetBagColor]!!

        return 1 + contained.sumOf { it.amount * necessaryBagsFor(it.color) }
    }

    private fun doesContain(container: String, target: String): Boolean {
        return (target in bagsContainedIn(container)) || bagsContainedIn(container).any { doesContain(it, target) }
    }

    private fun bagsContainedIn(containerBagColor: String) = relations[containerBagColor]!!.map { it.color }

    companion object {
        fun parse(input: String): BagsGraph {
            val relations = mutableMapOf<String, MutableList<Bags>>()

            val rows = input.split("\n")
            for (row in rows) {
                val (container, allContained) = row.removeSuffix(".").split(" contain ", limit = 2)

                val containerColor = container.removeSuffix(" bags")
                relations[containerColor] = mutableListOf()

                for (contained in allContained.split(", ")) {
                    if (contained == "no other bags") break

                    val regex = Regex("""(\d+) (.+) bags?""")
                    val (containedCount, containedColor) = regex.find(contained)!!.destructured
                    relations[containerColor]!!.add(Bags(containedCount.toInt(), containedColor))
                }
            }
            return BagsGraph(relations)
        }
    }
}
