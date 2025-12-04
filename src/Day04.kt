fun main() {
    fun part1(input: List<String>): Int {
        // Output Matrix
        input.forEach { it.println() }
        "".println()

        // Real code (Will output matrix like in shown in Day 4 example, by replacing "@" with "x")
        val grid = input.map { it.toList() }
        return grid.mapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, item ->
                if (item != '@') return@mapIndexed '.'
                listOf(
                    rowIndex - 1 to columnIndex - 1,
                    rowIndex - 1 to columnIndex,
                    rowIndex - 1 to columnIndex + 1,

                    rowIndex to columnIndex - 1,
                    rowIndex to columnIndex + 1,

                    rowIndex + 1 to columnIndex - 1,
                    rowIndex + 1 to columnIndex,
                    rowIndex + 1 to columnIndex + 1,
                ).mapNotNull { (adjacentRowIndex, adjacentColumnIndex) ->
                    grid.getOrNull(adjacentRowIndex)?.getOrNull(adjacentColumnIndex)
                }.count { it == '@' }.let {
                    if (it < 4) {
                        'x'
                    } else {
                        '@'
                    }
                }
            }.also { it.joinToString("").println() }
        }.sumOf { rows -> rows.count { it == 'x' } }
    }

    fun part2(input: List<String>): Int {
        fun List<List<Char>>.markRemovableRolls(): List<List<Char>> {
            return mapIndexed { rowIndex, row ->
                row.mapIndexed { columnIndex, item ->
                    if (item != '@') return@mapIndexed '.'
                    listOf(
                        rowIndex - 1 to columnIndex - 1,
                        rowIndex - 1 to columnIndex,
                        rowIndex - 1 to columnIndex + 1,

                        rowIndex to columnIndex - 1,
                        rowIndex to columnIndex + 1,

                        rowIndex + 1 to columnIndex - 1,
                        rowIndex + 1 to columnIndex,
                        rowIndex + 1 to columnIndex + 1,
                    ).mapNotNull { (adjacentRowIndex, adjacentColumnIndex) ->
                        getOrNull(adjacentRowIndex)?.getOrNull(adjacentColumnIndex)
                    }.count { it == '@' }.let {
                        if (it < 4) {
                            'R'
                        } else {
                            '@'
                        }
                    }
                }.also { it.joinToString("").println() }
            }
        }

        fun List<List<Char>>.removeMarkedRolls(): Pair<List<List<Char>>, Int> {
            var removedRolls = 0
            val withoutRemovableRolls = map { row ->
                row.map { item ->
                    if (item == 'R') {
                        removedRolls++
                        '.'
                    } else {
                        item
                    }
                }
            }
            return withoutRemovableRolls to removedRolls
        }


        // Output Matrix
        input.forEach { it.println() }
        "".println()

        // Real code (Will output matrix like in shown in Day 4 example, by replacing "@" with "R" for removable rolls and with "x" after a roll was removed)
        var currentGridWithRemovedCount = input.map { it.toList() } to 0
        do {
            val currentGrid = currentGridWithRemovedCount.first
            val nextGridWithRemovedRollsCount = currentGrid.markRemovableRolls().removeMarkedRolls()
            "".println()
            currentGridWithRemovedCount = nextGridWithRemovedRollsCount.copy(
                second = currentGridWithRemovedCount.second + nextGridWithRemovedRollsCount.second
            )
        } while (nextGridWithRemovedRollsCount.second != 0)
        return currentGridWithRemovedCount.second
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