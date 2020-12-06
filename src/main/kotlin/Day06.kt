class Day06 {
    fun solvePart1(input: String): Int {
        return input
            .split("\n\n")
            .sumOf(::uniqueChars)
    }

    private fun uniqueChars(string: String) =
        string
            .replace("\n", "")
            .toCharArray()
            .distinct()
            .size
}
