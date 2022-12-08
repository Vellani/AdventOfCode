package solutions

fun d5t1(lines: List<String>) {

    val emptyRowIndex = lines.indexOfFirst { it.isBlank() }
    val totalStacks = lines[emptyRowIndex - 1].last().digitToInt()

    val arrayStack = Array(totalStacks) { ArrayDeque<Char>() }
    lines.forEachIndexed { sIndex, sVal ->
        if (sIndex < emptyRowIndex) {
            sVal.chunked(4).map { it[1] }.forEachIndexed { i, e -> if (e.code != 32) arrayStack[i].add(e) }
        } else if (sIndex > emptyRowIndex) {
            val co = sVal.split(" ")
            for (i in 0 until co[1].toInt()) {
                arrayStack[co[5].toInt() - 1].addFirst(arrayStack[co[3].toInt() - 1].removeFirst())
            }
        }
    }

    var sum = ""
    for (singleStack in arrayStack) {
        sum += singleStack.removeFirst()
    }

    println(sum)
}

fun d5t2(lines: List<String>) {

    val emptyRowIndex = lines.indexOfFirst { it.isBlank() }
    val totalStacks = lines[emptyRowIndex - 1].last().digitToInt()

    val arrayStack = Array(totalStacks) { ArrayDeque<Char>() }
    lines.forEachIndexed { sIndex, sVal ->
        if (sIndex < emptyRowIndex) {
            sVal.chunked(4).map { it[1] }.forEachIndexed { i, e -> if (e.code != 32) arrayStack[i].add(e) }
        } else if (sIndex > emptyRowIndex) {
            val co = sVal.split(" ")
            val tempArrayDeque = ArrayDeque<Char>()
            for (i in 0 until co[1].toInt()) {
                tempArrayDeque.add(arrayStack[co[3].toInt() - 1].removeFirst())
            }
            while (tempArrayDeque.isNotEmpty()) {
                arrayStack[co[5].toInt() - 1].addFirst(tempArrayDeque.removeLast())
            }
        }
    }

    var sum = ""
    for (singleStack in arrayStack) {
        sum += singleStack.removeFirst()
    }

    println(sum)
}

