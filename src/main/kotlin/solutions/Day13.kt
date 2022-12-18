package solutions

fun d13t1(lines: List<String>) {
    val message = lines.chunked(3)

    val folded = message.foldIndexed(0) { index, acc, e ->
        val depthListOne = buildNodes(e[0].replace("10", "a").toCharArray())
        val depthListTwo = buildNodes(e[1].replace("10", "a").toCharArray())

        val areOrdered = deepCompare(depthListOne as ArrayList<*>, depthListTwo as ArrayList<*>)
        if (areOrdered == 1 || areOrdered == 0) acc + index + 1 else acc
    }
    println(folded)
}

fun d13t2(lines: List<String>) {
    val list = arrayListOf("[[2]]", "[[6]]")
    list.addAll(lines)
    val answer = list
        .filter { it.isNotBlank() }
        .map { buildNodes(it.replace("10", "a").toCharArray()) as ArrayList<*> }
        .stream()
        .sorted { a1, a2 -> -deepCompare(a1, a2) }
        .toList().foldIndexed(1) { index, acc, e -> if (e == listOf<Any>(listOf<Any>(2)) || e == listOf<Any>(listOf<Any>(6))) acc * (index + 1) else acc }

    println(answer)
}

var index = 1
fun buildNodes(array: CharArray): Any {
    val node: ArrayList<Any> = arrayListOf()

    while (index < array.size - 1) {
        val char = array[index]
        index++

        if (char == '[') {
            node.add(buildNodes(array))
        } else if (char.code in 48..57 || char == 'a') {
            node.add(if (char == 'a') 10 else char.digitToInt())
        } else if (char == ']') {
            return node
        }
    }
    index = 1
    return node
}

fun deepCompare(depthListOne: ArrayList<*>, depthListTwo: ArrayList<*>): Int {
    if (depthListOne.isEmpty() && depthListTwo.isNotEmpty()) {
        return 1
    }
    for (i in depthListOne.indices) {
        val comparison =
            if (i >= depthListTwo.size || i >= depthListOne.size) {
                compareValuesBy(depthListTwo, depthListOne) { it.size }
            } else if (depthListOne[i] is Int && depthListTwo[i] is Int) {
                compareValues(depthListTwo[i] as Int, depthListOne[i] as Int)
            } else if (depthListOne[i] is ArrayList<*> && depthListTwo[i] is ArrayList<*>) {
                deepCompare(depthListOne[i] as ArrayList<*>, depthListTwo[i] as ArrayList<*>)
            } else {
                deepCompare(
                    if (depthListOne[i] is ArrayList<*>) depthListOne[i] as ArrayList<*> else arrayListOf(depthListOne[i]),
                    if (depthListTwo[i] is ArrayList<*>) depthListTwo[i] as ArrayList<*> else arrayListOf(depthListTwo[i])
                )
            }
        when (comparison) {
            -1, 1 -> return comparison
        }
    }
    return 0
}
