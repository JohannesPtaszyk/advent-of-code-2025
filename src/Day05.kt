fun main() {
    fun part1(input: List<String>): Int {
        val splitIndex = input.indexOfLast { it.isBlank() }
        val freshIdRanges = input
            .take(splitIndex)
            .map { idRangeStrings ->
                idRangeStrings
                    .split("-")
                    .let { it.first().toLong()..it.last().toLong() }
            }
        val ingredientIds = input
            .takeLast(input.size - splitIndex - 1)
            .map { it.toLong() }
        return ingredientIds.count { id ->
            val contained = freshIdRanges.find { it.contains(id) }
            contained != null
        }
    }

    fun part2(input: List<String>): Long {
        val splitIndex = input.indexOfLast { it.isBlank() }
        val freshIdRanges = input
            .take(splitIndex)
            .map { idRangeStrings ->
                idRangeStrings
                    .split("-")
                    .let { it.first().toLong()..it.last().toLong() }
            }
        val mergedRanges = freshIdRanges.runningFold(freshIdRanges) { ranges, range ->
            val overlapping = ranges.filter { range.contains(it.first) || range.contains(it.last) }
            if (overlapping.isEmpty()) {
                ranges
            } else {
                val merged = overlapping.minOf { it.first }..overlapping.maxOf { it.last }
                ranges.toMutableList().apply {
                    check(removeAll(overlapping))
                    add(merged)
                }
            }
        }
        return mergedRanges.last().sumOf {
            it.last - it.first + 1
        }
    }

    val testInput = readInput("Day05_test")
    val part1 = part1(testInput)
    println("Test Part 1: $part1")
    check(part1 == 3)

    val input = readInput("Day05")
    ("Part 1: " + part1(input)).println()

    val part2 = part2(testInput)
    println("Test Part 2: $part2")
    check(part2 == 14L)


    ("Part 2: " + part2(input)).println()
}
