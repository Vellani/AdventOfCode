package solutions

fun d3t1(lines: List<String>) {
    var sum = 0
    for (line in lines) {
        val map: HashMap<Char, Boolean> = hashMapOf()
        line.take(line.length / 2).toCharArray().forEach { map[it] = false }
        line.takeLast(line.length / 2).toCharArray().forEach { map.computeIfPresent(it) { _, _ -> true } }

        map.filter { it.value }.forEach { sum += if(it.key.code > 96) it.key.code - 96 else it.key.code - 38 }
    }
    print(sum)
}

fun d3t2(lines: List<String>) {
    var sum = 0

    val chunked = lines.chunked(3)


    for (i in 0 until lines.size - 2 step 3) {
        val (rowOne, rowTwo, rowThree) = lines.subList(i, i + 3)
        val map: HashMap<Char, Int> = hashMapOf()

        rowOne.toCharArray().forEach { map[it] = -1 }
        rowTwo.toCharArray().forEach { map.computeIfPresent(it) { _, _ -> 0 } }
        rowThree.toCharArray().forEach { map.computeIfPresent(it) { _, v -> if(v >= 0) 1 else v } }

        map.filter { it.value > 0 }.forEach { sum += if(it.key.code > 96) it.key.code - 96 else it.key.code - 38 }
    }
    print(sum)
}

fun d3t2v2(lines: List<String>) {
    var sum = 0

    for (i in 0 until lines.size - 2 step 3) {
        val array: Array<Int> = Array(123) { 0 }
        val (rowOne, rowTwo, rowThree) = lines.subList(i, i + 3)

        rowOne.chars().forEach { array[it] = 1 }
        rowTwo.chars().forEach { if(array[it] == 1) array[it] = 2 }
        rowThree.chars().forEach { if(array[it] == 2) array[it] = 3 }

        array.forEachIndexed { index, value -> if (value == 3) { sum += if (index > 96) index - 96 else index - 38 } }
    }
    print(sum)
}