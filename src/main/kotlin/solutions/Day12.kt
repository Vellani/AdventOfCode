package solutions


val queue = ArrayList<Block>()

fun d12t1(lines: List<String>) {
    val flatMap = lines.map { it.replace('E', '{').toCharArray() }
    val find = flatMap.mapIndexed { index, s -> index to s.indexOfFirst { it == 'S' } }.filter { it.second == 0 }[0]
    flatMap[find.first][find.second] = 'a'

    queue.add(Block(find.first, find.second, null))
    var index = 0
    while (index < queue.size) {
        val b = pathfinder(flatMap, index++)
        if (b != null) {
            print(calculatePath(b))
            break
        }
    }
}

// This is better solved by starting from E to any end on row 0 but I didn't feel like rewriting the whole task
fun d12t2(lines: List<String>) {
    val flatMap = lines.map { it.replace('E', '{').toCharArray() }
    val find = flatMap.mapIndexed { index, s -> index to s.indexOfFirst { it == 'S' } }.filter { it.second == 0 }[0]
    flatMap[find.first][find.second] = 'a'

    var minPath = Integer.MAX_VALUE

    for (i in flatMap.indices) {
        queue.add(Block(i, find.second, null))
        var index = 0
        while (index < queue.size) {
            val b = pathfinder(flatMap, index++)
            if (b != null) {
                minPath = minPath.coerceAtMost(calculatePath(b))
                break
            }
        }
        queue.removeAll { true }
    }
    println(minPath)
}

fun pathfinder(map: List<CharArray>, index: Int): Block? {
    val el = queue[index]

    val elValue = map[el.x][el.y]
    if (elValue == '{') {
        val block = Block(el.x, el.y, el.prev)
        //calculateAndPrintPath(map, block)
        return block
    }
    if (inScope(el.x - 1, el.y, elValue, map)) {
        queue.add(Block(el.x - 1, el.y, el.x to el.y))
    }
    if (inScope(el.x + 1, el.y, elValue, map)) {
        queue.add(Block(el.x + 1, el.y, el.x to el.y))
    }
    if (inScope(el.x, el.y - 1, elValue, map)) {
        queue.add(Block(el.x, el.y - 1, el.x to el.y))
    }
    if (inScope(el.x, el.y + 1, elValue, map)) {
        queue.add(Block(el.x, el.y + 1, el.x to el.y))
    }
    return null
}

fun inScope(x: Int, y: Int, oldValue: Char, map: List<CharArray>): Boolean {
    return x >= 0 && x < map.size && y >= 0 && y < map[0].size && oldValue.code - map[x][y].code >= -1 && !queue.contains(
        Block(x, y, null)
    )
}

fun calculatePath(finalBlock: Block): Int {
    var counter = 0
    var block = finalBlock
    while (block.prev != null) {
        counter++
        block = queue.find { it.x == block.prev!!.first && it.y == block.prev!!.second }!!
    }
    return counter
}

class Block(
    val x: Int,
    val y: Int,
    val prev: Pair<Int, Int>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Block

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }
}

// For matrix visualisation ( NOT NEEDED ) -----------------------------------------------------------------
fun calculateAndPrintPath(map: List<CharArray>, finalBlock: Block) {
    val list: ArrayList<ArrayList<Char>> = arrayListOf()
    map.forEach { s ->
        val innerList = arrayListOf<Char>()
        s.forEach { _ ->
            innerList.add(' ')
        }
        list.add(innerList)
    }
    var block = finalBlock
    while (block.prev != null) {
        list[block.x][block.y] = '█'
        block = queue.find { it.x == block.prev!!.first && it.y == block.prev!!.second }!!
    }
    list.forEach { e ->
        e.forEach { c ->
            print(c)
        }
        println()
    }

}

