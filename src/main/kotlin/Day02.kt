class Day02 {
    fun solvePart1(input: String): Int {
        return entriesFrom(input).count { it.isCorrect() }
    }

    private fun entriesFrom(input: String) =
        input.split("\n").map(::parseEntry)

    private fun parseEntry(inputRow: String): PasswordEntry {
        val (rangeStart, rangeEnd, letter, password) = passwordEntryRegex.find(inputRow)!!.destructured
        val allowedFrequency = IntRange(rangeStart.toInt(), rangeEnd.toInt())

        return PasswordEntry(PasswordPolicy(letter.first(), allowedFrequency), password)
    }

    companion object {
        private val passwordEntryRegex = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")
    }
}

private class PasswordEntry(
    private val passwordPolicy: PasswordPolicy,
    private val password: String
) {
    fun isCorrect() = passwordPolicy.check(password)
}

private class PasswordPolicy(
    private val letter: Char,
    private val allowedFrequency: IntRange
) {
    fun check(password: String) = password.count { it == letter } in allowedFrequency
}
