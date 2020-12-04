class Day04 {
    fun solvePart1(input: String) = parse(input).count { it.isValid() }

    private fun parse(input: String): Collection<Passport> {
        return input
            .split(PASSPORT_SEPARATOR)
            .map(Passport::parse)
    }

    companion object {
        const val PASSPORT_SEPARATOR = "\n\n"
    }
}

private class Passport private constructor(val keys: Set<String>){
    private val requiredKeys = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    fun isValid() = requiredKeys.all { it in keys }

    companion object {
        fun parse(input: String): Passport {
            return input
                .split("""\s""".toRegex())
                .map { it.split(":").first() }
                .let { Passport(it.toSet()) }
        }
    }
}
