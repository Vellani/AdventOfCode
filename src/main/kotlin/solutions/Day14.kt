package solutions

import writeToFile
import java.lang.Integer.min
import java.lang.Math.max


fun d14t1(lines: List<String>) {
    val scan = getCoordinates(lines)

    var count = 0
    while (true) {
        count++
        if (moveSand(0, 500, scan) == -1) {
            break
        }
    }
    println(count - 1)
    writeToFile(scan)
}

fun d14t2(lines: List<String>) {
    val scan = getCoordinates2(lines)

    var count = 0
    while (true) {
        count++
        when (moveSand(0, 500, scan)) {
            -1, 0 -> break
        }
    }
    println(count - 1)
    writeToFile(scan)
}


fun moveSand(h: Int, w: Int, scan: Array<Array<Char>>): Int {
    if (h < 0 || w < 0 || h >= scan.size || w >= scan[0].size) {
        return -1
    } else if (scan[h][w] != ' ') {
        return 0
    }

    when (val moveDown = moveSand(h + 1, w, scan)) {
        -1, 1 -> return moveDown
    }
    when (val moveLeft = moveSand(h + 1, w - 1, scan)) {
        -1, 1 -> return moveLeft
    }
    when (val moveRight = moveSand(h + 1, w + 1, scan)) {
        -1, 1 -> return moveRight
    }
    scan[h][w] = 'O'
    return 1
}

fun getCoordinates(lines: List<String>): Array<Array<Char>> {
    val platform = lines.map { it.split(" -> ").map { e -> e.split(",") }.map { e -> e[0].toInt() to e[1].toInt() } }
    val maxX = platform.fold(0) { acc, e -> acc.coerceAtLeast( e.fold(0) { acc2, e2 -> acc2.coerceAtLeast(e2.second) }) }
    val maxY = platform.fold(0) { acc, e -> acc.coerceAtLeast( e.fold(0) { acc2, e2 -> acc2.coerceAtLeast(e2.first) }) }
    val array = Array(maxX + 2) { Array(maxY + 1) { ' ' } }

    platform.forEach {
        for (i in 0 until it.size - 1) {
            for (y in min(it[i].first, it[i + 1].first) .. max(it[i].first, it[i + 1].first)) {
                array[it[i].second][y] = '█'
            }
            for (x in min(it[i].second, it[i + 1].second) .. max(it[i].second, it[i + 1].second) ) {
                array[x][it[i].first] = '█'
            }
        }
    }
    return array
}

fun getCoordinates2(lines: List<String>): Array<Array<Char>> {
    val platform = lines.map { it.split(" -> ").map { e -> e.split(",") }.map { e -> e[0].toInt() to e[1].toInt() } }
    val maxX = platform.fold(0) { acc, e -> acc.coerceAtLeast( e.fold(0) { acc2, e2 -> acc2.coerceAtLeast(e2.second) }) }
    val maxY = platform.fold(0) { acc, e -> acc.coerceAtLeast( e.fold(0) { acc2, e2 -> acc2.coerceAtLeast(e2.first) }) }
    val array = Array(maxX + 3) { e -> Array(maxY + 1000) { if (e == maxX + 2 ) '█' else ' '} }

    platform.forEach {
        for (i in 0 until it.size - 1) {
            for (y in min(it[i].first, it[i + 1].first) .. max(it[i].first, it[i + 1].first)) {
                array[it[i].second][y] = '█'
            }
            for (x in min(it[i].second, it[i + 1].second) .. max(it[i].second, it[i + 1].second) ) {
                array[x][it[i].first] = '█'
            }
        }
    }
    return array
}