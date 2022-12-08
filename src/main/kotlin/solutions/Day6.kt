package solutions

fun d6t1(lines: List<String>) {
    val queue = ArrayDeque<Char>()
    val chars = lines[0].toCharArray()

    for (i in lines[0].indices) {
        while (queue.contains(chars[i])) queue.removeFirst()
        queue.add(chars[i])
        if (queue.size == 4) return println("Task one: ${i + 1}")
    }
}

fun d6t2(lines: List<String>) {
    val queue = ArrayDeque<Char>()
    val chars = lines[0].toCharArray()

    for (i in lines[0].indices) {
        while (queue.contains(chars[i])) queue.removeFirst()
        queue.add(chars[i])
        if (queue.size == 4) return println("Task one: ${i + 1}")
    }
}