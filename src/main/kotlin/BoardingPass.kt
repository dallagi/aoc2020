data class BoardingPass(val row: Int, val column: Int) {
    fun seatId() = row * 8 + column

    companion object {
        fun parse(input: String): BoardingPass {
            return BoardingPass(
                decodeNumber(input.slice(0..6), 'F', 'B'),
                decodeNumber(input.slice(7..9), 'L', 'R')
            )
        }

        private fun decodeNumber(encoded: String, zeroChar: Char, oneChar: Char): Int {
            return encoded
                .replace(zeroChar, '0')
                .replace(oneChar, '1')
                .toInt(radix = 2)
        }
    }
}
