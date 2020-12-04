// Solves Day 4: Passport Processing https://adventofcode.com/2020/day/4
class Day04 {
    fun solvePart1(input: String) = parse(input).count { it.hasRequiredKeys() }

    fun solvePart2(input: String) = parse(input).count { it.isValid() }

    private fun parse(input: String) =
        input
            .split(PASSPORT_SEPARATOR)
            .map(Passport::parse)

    companion object {
        const val PASSPORT_SEPARATOR = "\n\n"
    }
}

private class Passport private constructor(val attributes: Map<String, String>) {

    fun hasRequiredKeys() = rules.keys.all { it in attributes.keys }

    fun isValid() = hasRequiredKeys() && rules.all { checkRule(it.key, it.value) }

    private fun checkRule(key: String, rule: (String) -> Boolean) =
        attributes[key]
            ?.let { attribute -> rule(attribute) }
            ?: false

    companion object {
        fun parse(input: String) = input
            .split("""\s""".toRegex())
            .map(::parseAttribute)
            .let { Passport(it.toMap()) }

        private fun parseAttribute(attribute: String) =
            attribute
                .split(":")
                .let { parts -> parts[0] to parts[1] }

        private val rules = mapOf(
            "byr" to { v: String -> v.length == 4 && v.toInt() in 1920..2002 },
            "iyr" to { v: String -> v.length == 4 && v.toInt() in 2010..2020 },
            "eyr" to { v: String -> v.length == 4 && v.toInt() in 2020..2030 },
            "hgt" to ::checkHeight,
            "hcl" to { v: String -> Regex("""^#([0-9a-f]){6}$""").matches(v) },
            "ecl" to { v: String -> v in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
            "pid" to { v: String -> Regex("""^[0-9]{9}$""").matches(v) })

        private fun checkHeight(height: String) = when {
            height.endsWith("cm") -> height.removeSuffix("cm").toInt().let { it in 150..193 }
            height.endsWith("in") -> height.removeSuffix("in").toInt().let { it in 59..76 }
            else -> false
        }
    }
}
