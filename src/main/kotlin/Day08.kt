import java.lang.RuntimeException

class Day08 {
    fun solvePart1(input: String) =
        Interpreter(parse(input))
            .apply { runUntilLoop() }
            .accumulator

    fun solvePart2(input: String): Int {
        val originalInstructions = parse(input)

        for (mutatedInstructions in mutations(originalInstructions)) {
            val interpreter = Interpreter(mutatedInstructions)

            runCatching(interpreter::run)
                .onSuccess { return interpreter.accumulator }
        }
        return 0
    }

    private fun mutations(originalInstructions: List<Instruction>) = sequence {
        for ((index, instruction) in originalInstructions.withIndex()) {
            if (instruction.code == "acc") continue

            yield(swapNopAndJmpAt(index, originalInstructions))
        }
    }

    private fun swapNopAndJmpAt(index: Int, originalInstructions: List<Instruction>) =
        originalInstructions
            .toMutableList()
            .apply { set(index, swapNopAndJmp(originalInstructions[index])) }

    private fun swapNopAndJmp(instruction: Instruction) =
        when (instruction.code) {
            "nop" -> Instruction("jmp", instruction.argument)
            "jmp" -> Instruction("nop", instruction.argument)
            else -> instruction
        }

    private fun parse(input: String) =
        input
            .split("\n")
            .map(::parseInstruction)

    private fun parseInstruction(instructionRow: String) =
        instructionRow
            .split(" ", limit = 2)
            .let { (code, arg) -> Instruction(code, arg.toInt()) }
}

private data class Instruction(val code: String, val argument: Int)

private class Interpreter(private val instructions: List<Instruction>) {
    class LoopException(message: String) : RuntimeException(message)

    var accumulator = 0
    private var instructionPointer = 0
    private var instructionsSeen = mutableSetOf<Int>()

    fun runUntilLoop() {
        runCatching(::run)
    }

    fun run() {
        while (true) {
            if (instructionAlreadySeen()) throw LoopException("A loop occured")
            if (programFinished()) break
            processInstruction()
        }
    }

    private fun programFinished() = instructionPointer == instructions.lastIndex + 1

    private fun processInstruction() {
        instructionsSeen.add(instructionPointer)

        val instruction = instructions[instructionPointer]
        when (instruction.code) {
            "nop" -> instructionPointer++
            "jmp" -> instructionPointer += instruction.argument
            "acc" -> {
                accumulator += instruction.argument
                instructionPointer++
            }
        }

    }

    private fun instructionAlreadySeen() = instructionPointer in instructionsSeen
}