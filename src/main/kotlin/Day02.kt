// Solves Day 2: Password Philosophy https://adventofcode.com/2020/day/2
class Day02 {
    fun solvePart1(input: String): Int {
        return entriesFrom(input).count(::correctByFrequency)
    }

    fun solvePart2(input: String): Int {
        return entriesFrom(input).count(::correctByPosition)
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

private data class PasswordEntry(
    val policyNumbers: Pair<Int, Int>,
    val policyLetter: Char,
    val password: String
)

private fun correctByFrequency(passwordEntry: PasswordEntry) = with(passwordEntry) {
    val allowedFrequency = IntRange(policyNumbers.first, policyNumbers.second)

    password.count { it == policyLetter } in allowedFrequency
}

private fun correctByPosition(passwordEntry: PasswordEntry) = with(passwordEntry) {
    val matchesAt = { index: Int -> password.getOrNull(index-1) == policyLetter }

    matchesAt(policyNumbers.first) xor matchesAt(policyNumbers.second)
}
