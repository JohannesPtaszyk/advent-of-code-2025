fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { bankString ->
            val bank = bankString.toList().map { it.digitToInt() }
            val potentialFirstDigits = bank.take(bank.size - 1)
            var highestNonLastIndex = -1
            potentialFirstDigits.forEachIndexed { i, battery ->
                highestNonLastIndex = when {
                    highestNonLastIndex == -1 -> i
                    bank[highestNonLastIndex] < battery -> i
                    else -> highestNonLastIndex
                }
            }
            val highestSecondNumber = bank.subList(highestNonLastIndex + 1, bank.size).maxOf { it }
            "${bank[highestNonLastIndex]}$highestSecondNumber".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day03_test")
    val part1Test = part1(testInput)
    println("Test Part 1: $part1Test")
    check(part1Test == 357)

    val input = readInput("Day03")
    ("Part 1: " + part1(input)).println()

    val part2Test = part2(testInput)
    println("Test Part 2: $part2Test")
    check(part2Test == 6)


    ("Part 2: " + part2(input)).println()
}
