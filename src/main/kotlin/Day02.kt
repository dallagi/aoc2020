// Solves Day 2: Password Philosophy https://adventofcode.com/2020/day/2
class Day02 {
    fun solvePart1(input: String): Int {
        return entriesFrom(input).count { it.correctByFrequency() }
    }

    fun solvePart2(input: String): Int {
        return entriesFrom(input).count { it.correctByPosition() }
    }

    private fun entriesFrom(input: String) = input.split("\n").map(::parseEntry)

    private fun parseEntry(inputRow: String): PasswordEntry {
        val (firstNum, secondNum, letter, password) = passwordEntryRegex.find(inputRow)!!.destructured

        return PasswordEntry(
            Pair(firstNum.toInt(), secondNum.toInt()),
            letter.first(),
            password
        )
    }

    companion object {
        private val passwordEntryRegex = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")
    }
}

private class PasswordEntry(
    private val policyNumbers: Pair<Int, Int>,
    private val policyLetter: Char,
    private val password: String
) {
    fun correctByFrequency() = password.count { it == policyLetter } in policyNumbers.toRange()

    fun correctByPosition() = matchesAt(policyNumbers.first) xor matchesAt(policyNumbers.second)

    private fun matchesAt(index: Int) = password.getOrNull(index - 1) == policyLetter
}

private fun Pair<Int, Int>.toRange() = IntRange(first, second)