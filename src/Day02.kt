fun main() {
    fun part1(input: List<String>): Long {
        fun String.isInvalid(): Boolean {
            val first = substring(0, length / 2)
            val last = substring(length / 2, length)
            return first == last
        }

        return input
            .first()
            .split(",")
            .map { pair -> pair.split("-") }
            .also { it.println() }
            .filter { strings -> strings.none { it.toLongOrNull() == null } }
            .flatMap { ids ->
                val idRange = ids.first().toLong()..ids.last().toLong()
                idRange.filter { it.toString().isInvalid() }.also { it.println() }
            }
            .also { it.println() }
            .sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    val part1 = part1(testInput)
    println("Test Part 1: $part1")
    check(part1 == 1227775554L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    ("Part 1: " + part1(input)).println()


    val part2 = part2(testInput)
    println("Test Part 2: $part2")
    check(part2 == 6)

    ("Part 2: " + part2(input)).println()
}
