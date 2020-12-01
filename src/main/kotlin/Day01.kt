class Day01 {
    fun solvePart1(input: String): Int? {
        val numbers = parse(input)
        return productOfPairThatSumsTo2020(numbers)
    }

    private fun productOfPairThatSumsTo2020(numbers: List<Int>): Int? {
        val sortedNumbers = numbers.sorted()
        val target = 2020

        var left = 0
        var right = numbers.lastIndex

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