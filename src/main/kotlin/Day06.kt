class Day06 {
    fun solvePart1(input: String): Int {
        return input
            .split("\n\n")
            .sumOf(::allAnswers)
    }

    fun solvePart2(input: String): Int {
        return input
            .split("\n\n")
            .sumOf(::commonAnswers)
    }

    private fun allAnswers(string: String) =
        string
            .replace("\n", "")
            .toCharArray()
            .distinct()
            .size

    private fun commonAnswers(answers: String) =
        answers
            .split("\n")
            .map { singlePersonAnswers -> singlePersonAnswers.toSet() }
            .reduce { acc, i -> acc intersect i }
            .size
}
