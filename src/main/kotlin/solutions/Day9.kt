package solutions

import kotlin.math.abs

fun d9t1(lines: List<String>) {

    val head = Array(2) { 0 }
    val tail = Array(2) { 0 }
    val set = linkedSetOf("0 0")

    for (line in lines) {
        val op = line.split(" ")

        for (i in 0 until op[1].toInt()) {

            when (op[0]) {
                "L" -> head[0] -= 1
                "R" -> head[0] += 1
                "U" -> head[1] -= 1
                "D" -> head[1] += 1
            }

            if (abs(tail[0] - head[0]) > 1 || abs(tail[1] - head[1]) > 1) {
                tail[0] += if (head[0] - tail[0] > 0) 1 else if (head[0] - tail[0] == 0) 0 else -1
                tail[1] += if (head[1] - tail[1] > 0) 1 else if (head[1] - tail[1] == 0) 0 else -1
                set.add("${tail[0]} ${tail[1]}")
            }
        }
    }
    println(set.size)
}


fun d9t2(lines: List<String>) {

    val rope = Array(10) { Array(2) { 0 } }
    val set = linkedSetOf("0 0")

    for (line in lines) {
        val op = line.split(" ")

        for (i in 0 until op[1].toInt()) {

            when (op[0]) {
                "U" -> rope[0][0] -= 1
                "D" -> rope[0][0] += 1
                "L" -> rope[0][1] -= 1
                "R" -> rope[0][1] += 1
            }

            for (j in 1 until rope.size) {
                if (abs(rope[j][0] - rope[j - 1][0]) > 1 || abs(rope[j][1] - rope[j - 1][1]) > 1) {
                    rope[j][0] += if (rope[j - 1][0] - rope[j][0] > 0) 1 else if (rope[j - 1][0] - rope[j][0] == 0) 0 else -1
                    rope[j][1] += if (rope[j - 1][1] - rope[j][1] > 0) 1 else if (rope[j - 1][1] - rope[j][1] == 0) 0 else -1

                    if (j == rope.size - 1) {
                        set.add("${rope[rope.size - 1][0]} ${rope[rope.size - 1][1]}")
                    }
                } else {
                    break
                }
            }
        }
    }
    println(set.size)
}
