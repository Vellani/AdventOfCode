package solutions

import kotlin.math.abs

const val row = 2000000L

fun d15t1(lines: List<String>) {

    val scanned = HashSet<Long>()
    val beaconsOnTheLine = HashSet<Pair<Long, Long>>()
    lines
        .map {
            val c = it.replace("Sensor at x=", "").replace(", y=", " ").replace(": closest beacon is at x=", " ").split(" ")
            if (c[3].toLong() == row) beaconsOnTheLine.add(c[2].toLong() to c[3].toLong())
            Beacon(x = c[0].toLong(), y = c[1].toLong(), xb = c[2].toLong(), yb = c[3].toLong())
        }.forEach {
            crateRange(it, 2000000)?.forEach { e -> scanned.add(e) }
        }
    println(scanned.size - beaconsOnTheLine.size)
}

fun d15t2(lines: List<String>) {
    val min = 0L
    val max = 4000000L
    for (i in min .. max) {
        val scanned: ArrayList<Pair<Long, Long>> = arrayListOf()
        lines
            .map {
                val c = it.replace("Sensor at x=", "").replace(", y=", " ").replace(": closest beacon is at x=", " ")
                    .split(" ")
                Beacon(x = c[0].toLong(), y = c[1].toLong(), xb = c[2].toLong(), yb = c[3].toLong())
            }.forEach {
                val range = crateRange2(it, i)
                if (range != null) scanned.add(range)
            }
        val sortedRanges = scanned.sortedWith(compareBy({ it.first }, { -it.second }))

        var totalRange = sortedRanges[0]

        var index = 0
        while (index < sortedRanges.size - 1) {
            if (totalRange.second >= sortedRanges[index + 1].first && totalRange.second < sortedRanges[index + 1].second) {
                totalRange = totalRange.first to sortedRanges[index + 1].second
                index = 0
            } else {
                index++
            }
        }

        if (totalRange.first != min || totalRange.second != max) {
            println("Found gap on row $i and column ${totalRange.second + 1}")
            println(((totalRange.second + 1) * max) + (i))
            break
        }

        println(i)
    }
}

fun crateRange(it: Beacon, rowTarget: Long): LongRange? {
    val rowWidthReach = when (rowTarget)  {
        in (it.y - it.reach .. it.y) -> rowTarget - (it.y - it.reach)
        in (it.y .. it.y + it.reach) -> (it.y + it.reach) - rowTarget
        else -> return null
    }
    return (it.x - rowWidthReach .. it.x + rowWidthReach)
}

fun crateRange2(it: Beacon, rowTarget: Long): Pair<Long, Long>? {
    val rowWidthReach = when (rowTarget)  {
        in (it.y - it.reach .. it.y) -> rowTarget - (it.y - it.reach)
        in (it.y .. it.y + it.reach) -> (it.y + it.reach) - rowTarget
        else -> return null
    }
    return ((it.x - rowWidthReach).coerceAtLeast(0) to (it.x + rowWidthReach).coerceAtMost(4000000))
}

data class Beacon(val x: Long, val y: Long, val xb: Long, val yb: Long) {
    var reach = 0L
    init {
        reach = abs(x - xb) + abs(y - yb)
    }
}


