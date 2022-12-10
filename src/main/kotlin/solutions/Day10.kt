package solutions

fun d10t1(lines: List<String>) {
    var registerX = 1
    var sum = 0
    var cycle = 0
    for (line in 1 .. lines.size ) {
        val op = lines[line - 1].split(" ")
        sum += executeCycle1(++cycle, registerX)

        if (op[0] == "addx") {
            sum += executeCycle1(++cycle, registerX)
            registerX += op[1].toInt()
        }
    }
    println(sum)
}

fun executeCycle1(cycle: Int, registerX: Int): Int {
    return if (cycle in listOf(20, 60, 100, 140, 180, 220)) {
        registerX * cycle
    } else 0
}

fun d10t2(lines: List<String>) {
    var registerX = 1
    val sb = StringBuffer()
    var cycle = 0
    for (line in 1 .. lines.size ) {
        val op = lines[line - 1].split(" ")
        sb.append(executeCycle2(++cycle, registerX))

        if (op[0] == "addx") {
            sb.append(executeCycle2(++cycle, registerX))
            registerX += op[1].toInt()
        }
    }
    println(sb.toString())
}

fun executeCycle2(cycle: Int, registerX: Int): StringBuffer {
    val tempBuffer = StringBuffer()

    tempBuffer.append(if (listOf(registerX - 1, registerX, registerX + 1).contains((cycle - 1) % 40)) "##" else "  ")

    if (cycle in listOf(40, 80, 120, 160, 200, 240)) {
        tempBuffer.append(System.lineSeparator())
    }
    return tempBuffer
}