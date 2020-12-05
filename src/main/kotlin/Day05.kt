// Solves Day 5: Binary Boarding https://adventofcode.com/2020/day/5
class Day05 {
    fun solvePart1(input: String) = boardingPasses(input).maxOf { it.seatId() }

    fun solvePart2(input: String) = firstMissingSeatId(boardingPasses(input))

    private fun firstMissingSeatId(boardingPasses: List<BoardingPass>) =
        boardingPasses
            .map { it.seatId() }
            .sorted()
            .windowed(2)
            .find(::gapBetweenSeatIds)
            ?.let { seatIds -> seatIds[0] + 1 }

    private fun gapBetweenSeatIds(seatIds: List<Int>) =
        seatIds.last() - seatIds.first() > 1

    private fun boardingPasses(input: String): List<BoardingPass> {
        return input.split("\n").map(BoardingPass::parse)
    }
}
