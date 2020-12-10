class Day10 {
    fun solvePart1(input: String): Int {
        val differences = differencesBetweenSortedPairs(parse(input))
        return differences.count { it == 1 } * differences.count { it == 3 }
    }

    fun solvePart2(input: String): Long {
        return countOfPossibleChains(parse(input))
    }

    private fun differencesBetweenSortedPairs(numbers: List<Int>): List<Int> {
        val allNumbers = allNumbersFrom(numbers)

        return allNumbers
            .windowed(2)
            .map(::difference)
    }

    private fun difference(elements: List<Int>) = elements[1] - elements[0]

    private fun countOfPossibleChains(numbers: List<Int>): Long {
        val allNumbers = allNumbersFrom(numbers)
        val cache = mutableMapOf<Int, Long?>()

        return countOfPossibleChainsStartingFrom(0, allNumbers, cache)
    }

    private fun countOfPossibleChainsStartingFrom(index: Int, numbers: List<Int>, cache: MutableMap<Int, Long?>): Long {
        cache.getOrDefault(index, null)?.let { return it }

        if (index == numbers.lastIndex) return 1

        return indexOfPossibleNextNumbers(numbers, index)
            .mapNotNull { nextNumberIndex -> countOfPossibleChainsStartingFrom(nextNumberIndex, numbers, cache) }
            .sum()
            .also { cache[index] = it }
    }

    private fun indexOfPossibleNextNumbers(numbers: List<Int>, startIndex: Int) = sequence {
        for (i in (startIndex + 1)..(numbers.lastIndex)) {
            if(numbers[i] - numbers[startIndex] > 3) return@sequence

            yield(i)
        }
    }

    private fun allNumbersFrom(numbers: List<Int>): List<Int> {
        val myAdapter = listOf(numbers.maxOrNull()!! + 3)
        val firstAdapter = listOf(0)
        return firstAdapter + numbers.sorted() + myAdapter
    }

    private fun parse(input: String) = input.split("\n").map { it.toInt() }
}
