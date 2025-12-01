fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map { instruction ->
                val direction = instruction.take(1)
                val steps = instruction.substring(1, instruction.length).toInt()
                direction to steps
            }
            .runningFold(50 to 0) { (currentPointer, timesZero), (direction, steps) ->
                val newPointer = when (direction) {
                    "L" -> (currentPointer - steps) % 100
                    "R" -> (currentPointer + steps) % 100
                    else -> throw IllegalArgumentException("Direction unclear")
                }
                val newTimesZero = if(newPointer == 0) timesZero + 1 else timesZero
                newPointer to newTimesZero

            }
            .last()
            .second
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    val part1 = part1(testInput)
    println("Test Part 1: $part1")
    check(part1 == 3)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    ("Part 1: " + part1(input)).println()


    val part2 = part2(testInput)
    println("Test Part 2: $part2")
    check(part2 == 6)

    ("Part 2: " + part2(input)).println()
}
