fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day01_test")
    val part1 = part1(testInput)
    println("Test Part 1: $part1")
    check(part1 == 3)

    val input = readInput("Day01")
    ("Part 1: " + part1(input)).println()

    val part2 = part2(testInput)
    println("Test Part 2: $part2")
    check(part2 == 6)


    ("Part 2: " + part2(input)).println()
}
