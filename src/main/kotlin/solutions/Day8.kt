package solutions

fun d8t1(lines: List<String>) {
    var sum = 0
    val map: Array<Array<Boolean>> = Array(lines.size) { c -> Array(lines.size) { x -> x == 0 || x == lines.size - 1 || c == 0 || c == lines.size - 1 } }

    for (i in 1 until lines.size) {
        var downMovingMax = lines[0][i]
        var upMovingMax = lines[lines.size - 1][i]
        var rightMovingMax = lines[i][0]
        var leftMovingMax = lines[i][lines.size - 1]

        for (j in 1 until lines.size) {
            downMovingMax = look1(j, i, downMovingMax, lines, map)
            upMovingMax = look1(lines.size - j, i, upMovingMax, lines, map)
            rightMovingMax = look1(i, j, rightMovingMax, lines, map)
            leftMovingMax = look1(i, lines.size - j, leftMovingMax, lines, map)
        }
    }
    map.forEach { it.forEach { e -> if (e) sum++ } }
    println(sum)
}

fun look1(row: Int, col: Int, max: Char, list: List<String>, map: Array<Array<Boolean>>) : Char {
    if (list[row][col] > max) {
        map[row][col] = true
        return list[row][col]
    }
    return max
}


fun d8t2(lines: List<String>) {
    var maxSum = 0

    for (i in 1 until lines.size) {
        for (j in 1 until lines.size) {
            var localMax = 1

            localMax *= look2(i, j, lines, { x -> x }, { y -> y - 1 })
            localMax *= look2(i, j, lines, { x -> x }, { y -> y + 1 })
            localMax *= look2(i, j, lines, { x -> x - 1}, { y -> y })
            localMax *= look2(i, j, lines, { x -> x + 1}, { y -> y })

            maxSum = maxSum.coerceAtLeast(localMax)
        }
    }
    println(maxSum)
}

fun look2(row: Int, col: Int, list: List<String>, changeRow: (r: Int) -> Int, changeCol: (c: Int) -> Int): Int {
    var totalSum = 0
    val value = list[row][col]
    var newRow = row
    var newCol = col

    while (newRow > 0 && newRow < list.size - 1 && newCol > 0 && newCol < list.size - 1) {
        newRow = changeRow.invoke(newRow)
        newCol = changeCol.invoke(newCol)

        totalSum++

        if (value <= list[newRow][newCol]) {
            break
        }
    }
    return totalSum

}