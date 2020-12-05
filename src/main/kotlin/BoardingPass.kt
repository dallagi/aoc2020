import java.lang.Exception
import kotlin.math.pow

data class BoardingPass(val row: Int, val column: Int) {
    fun seatId() = row * 8 + column

    companion object {
        fun parse(input: String): BoardingPass {
            return BoardingPass(
                binarySearch(input.slice(0..6), 'B', 'F'),
                binarySearch(input.slice(7..9), 'R', 'L')
            )
        }

        private fun binarySearch(input: String, upChar: Char, downChar: Char): Int {
            var left = 0
            var right = (2 pow input.length) - 1

            for ((index, char) in input.withIndex()) {
                val sliceSize = 2 pow (input.length - (index + 1))

                when (char) {
                    upChar -> left += sliceSize
                    downChar -> right -= sliceSize
                    else -> throw Exception("Unsupported char")
                }
            }

            if (left != right) throw Exception("Something went wrong")

            return left
        }
    }
}

infix fun Int.pow(exponent: Int): Int = toDouble().pow(exponent).toInt()