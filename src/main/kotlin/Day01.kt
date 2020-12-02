
// Solves Day 1: Report Repair https://adventofcode.com/2020/day/1
// The simplest solution would have been to try all possible combinations.
// This solution is slightly more complex but more efficient, at O(nlogn) time
// complexity for part 1 and O(n^2) for part 2, instead of O(n^2) and O(n^3).
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