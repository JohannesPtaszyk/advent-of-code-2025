fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    "".println()
    "---- PART 1 ----".println()
    "".println()

    val testInput = readInput("Day04_test")
    val part1 = part1(testInput)
    println("Test Part 1: $part1")
    check(part1 == 13)

    val input = readInput("Day04")
    ("Part 1: " + part1(input)).println()

    "".println()
    "---- PART 2 ----".println()
    "".println()

    val part2 = part2(testInput)
    println("Test Part 2: $part2")
    check(part2 == 43)


    ("Part 2: " + part2(input)).println()
}
