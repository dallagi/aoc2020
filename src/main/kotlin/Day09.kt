class Day09 {
    fun solvePart1(input: String, preambleSize: Int) = firstNumberWithInvalidPreamble(parseInput(input), preambleSize)

    fun solvePart2(input: String, preambleSize: Int): Long {
        val allNumbers = parseInput(input)
        val invalidNumber = firstNumberWithInvalidPreamble(allNumbers, preambleSize)

        return sumOfContiguosNumbersThatSumToInvalidNumber(allNumbers, invalidNumber)
    }

    private fun firstNumberWithInvalidPreamble(allNumbers: List<Long>, preambleSize: Int) =
        allNumbers
            .windowed(preambleSize + 1)
            .first { numbers ->
                val element = numbers.last()
                val preamble = numbers.dropLast(1)

                !preambleCorrectFor(element, preamble)
            }
            .last()

    private fun sumOfContiguosNumbersThatSumToInvalidNumber(allNumbers: List<Long>, invalidNumber: Long): Long {
        (MIN_WINDOW_SIZE..allNumbers.size).forEach { windowSize ->
            allNumbers
                .windowed(windowSize)
                .firstOrNull { it.sum() == invalidNumber }
                ?.let { return it.minOrNull()!! + it.maxOrNull()!! }
        }
        return -1
    }

    private fun preambleCorrectFor(element: Long, preamble: List<Long>): Boolean {
        for ((index, a) in preamble.withIndex()) {
            for (b in preamble.takeLast(preamble.lastIndex - index)) {
                if (a + b == element) {
                    return true
                }
            }
        }
        return false
    }

    private fun parseInput(input: String) = input.split("\n").map { it.toLong() }

    companion object {
        private const val MIN_WINDOW_SIZE = 2
    }
}
