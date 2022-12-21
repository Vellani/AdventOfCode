package solutions


fun d1t1(lines: List<String>) {
    var maxSum = 0
    var localMax = 0

    for (line in lines) {

        if (line.isBlank()) {
            maxSum = maxSum.coerceAtLeast(localMax)
            localMax = 0
        } else {
            localMax += line.toInt()
        }
    }
    println(maxSum)
}

fun d1t2(lines: List<String>) {

    val maxList: ArrayList<Int> = arrayListOf(0)
    var index = 0
    for (line in lines) {
        if (line.isBlank()) {
            index++
            maxList.add(0)
        } else {
            maxList[index] = maxList[index] + line.toInt()
        }
    }
    maxList.sortDescending()

    println(maxList.take(3).sum())
}