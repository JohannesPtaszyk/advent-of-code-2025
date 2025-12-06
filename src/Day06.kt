import kotlin.collections.runningFold

fun main() {
    fun part1(input: List<String>): Long {
        data class Problem(
            val numbers: List<Long>,
            val type: Char = '.',
        )
        val problems = mutableListOf<Problem>()
        input.forEach { row ->
            if(row.any { it.digitToIntOrNull() != null }) {
                row.split(" ").mapNotNull { it.toLongOrNull() }.forEachIndexed { i, number ->
                    val problem = problems.getOrNull(i)
                    if(problem == null) {
                        problems += Problem(listOf(number))
                    } else {
                        problems[i] = problem.copy(numbers = problem.numbers + number)
                    }
                }
            } else {
                row.split(" ").filter { !it.isBlank() }.forEachIndexed { i, type ->
                    problems[i] = problems[i].copy(type = type.first())
                }
            }
        }
        return problems.sumOf {
            it.numbers.runningFold<Long, Long?>(null) { acc, number ->
                when (it.type) {
                    '+' -> (acc ?: 0L) + number
                    '*' -> (acc ?: 1L) * number
                    else -> error("Type unsupported")
                }
            }.filterNotNull().last()
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day06_test")
    val part1 = part1(testInput)
    println("Test Part 1: $part1")
    check(part1 == 4277556L)

    val input = readInput("Day06")
    ("Part 1: " + part1(input)).println()

    val part2 = part2(testInput)
    println("Test Part 2: $part2")
    check(part2 == 14)


    ("Part 2: " + part2(input)).println()
}
