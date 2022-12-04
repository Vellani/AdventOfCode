package solutions

fun dd2t1(lines: List<String>) {
    val map: Map<Char, Int> = mapOf(
        'A' to 1,
        'B' to 2,
        'C' to 3,
        'X' to 1,
        'Y' to 2,
        'Z' to 3
    )
    var sum = 0
    for (line in lines) {
        val (op, _, us) = line.toCharArray()


        sum += map[us]!! + when(map[us]!! - map[op]!!) {
            -2, 1 -> 6
            -1, 2 -> 0
            else -> 3
        }
    }
    print(sum)
}

fun d2t2(lines: List<String>) {
    val map: Map<Char, Int> = mapOf(
        '@' to 3,
        'A' to 1,
        'B' to 2,
        'C' to 3,
        'D' to 1,
        'X' to 0,
        'Y' to 3,
        'Z' to 6
    )
    var sum = 0
    for (line in lines) {
        val (op, _, us) = line.toCharArray()

        sum += map[us]!! + map[when(us) {
            'X' -> op - 1
            'Z' -> op + 1
            else -> op
        }]!!
    }
    print(sum)
}