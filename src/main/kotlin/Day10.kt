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

        if (index == numbers.lastIndex) {
            return 1
        }

        val number = numbers[index]
        val indexOfPossibleNextNumbers = (index + 1..index + 3)

        return indexOfPossibleNextNumbers.mapNotNull { possibleNextNumberIndex ->
            numbers
                .getOrNull(possibleNextNumberIndex)
                ?.let { possibleNextNumber ->
                    when (possibleNextNumber) {
                        in number..(number + 3) -> countOfPossibleChainsStartingFrom(
                            possibleNextNumberIndex,
                            numbers,
                            cache
                        )
                        else -> 0
                    }
                }
        }.sum().also { cache[index] = it }
    }

    private fun allNumbersFrom(numbers: List<Int>): List<Int> {
        val myAdapter = listOf(numbers.maxOrNull()!! + 3)
        val firstAdapter = listOf(0)
        return firstAdapter + numbers.sorted() + myAdapter
    }

    private fun parse(input: String) = input.split("\n").map { it.toInt() }
}
