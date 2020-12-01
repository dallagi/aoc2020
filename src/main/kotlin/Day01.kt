class Day01 {
    fun solvePart1(input: String): Int? {
        val numbers = parse(input)
        return productOfPairThatSumsTo(2020, numbers.sorted())
    }

    fun solvePart2(input: String): Int? {
        val numbers = parse(input)
        return productOfTripleThatSumsTo2020(numbers.sorted())
    }

    private fun productOfTripleThatSumsTo2020(sortedNumbers: List<Int>): Int? {
        for ((index, number) in sortedNumbers.withIndex()) {
            if(number > 2020) break
            val remainingTo2020 = 2020 - number

            productOfPairThatSumsTo(remainingTo2020, sortedNumbers, index + 1)
                ?.let { return it * number }
        }
        return null
    }

    private fun productOfPairThatSumsTo(target: Int, sortedNumbers: List<Int>, startIndex: Int = 0): Int? {
        var left = startIndex
        var right = sortedNumbers.lastIndex

        while (left < right) {
            val currentSum = sortedNumbers[left] + sortedNumbers[right]
            when {
                currentSum == target -> return sortedNumbers[left] * sortedNumbers[right]
                currentSum < target -> left++
                currentSum > target -> right--
            }
        }
        return null
    }

    private fun parse(input: String) = input.split("\n").map { it.toInt() }
}