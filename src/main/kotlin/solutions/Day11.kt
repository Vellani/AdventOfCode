package solutions

class Monkey(entity: List<String>) {
    var passes = 0L
    val items = entity[1].split(":")[1].split(",").map { it.trim().toLong() }.toCollection(ArrayDeque())
    val operation: (Long) -> Long = { x ->
        val op = entity[2].split("=")[1].trim().split(" ")
        if(op[1] == "+") {
            if (op[2] == "old") x + x
            else x + op[2].toInt()
        } else {
            if (op[2] == "old") x * x
            else x * op[2].toInt()
        }
    }
    val test: (Long) -> Boolean = { x -> x % entity[3].split(" ").last().toLong() == 0L }
    val targetOnTrue = entity[4].takeLast(1).toInt()
    val targetOnFalse = entity[5].takeLast(1).toInt()
    val divisor = entity[3].split(" ").last().toLong() // Needed only for part 2
}

fun d11t1(lines: List<String>) {
    val monkeys = lines.chunked(7).map { Monkey(it) }

    repeat(20) {
        monkeys.forEach { monkey ->
            while(monkey.items.size > 0) {
                monkey.passes++
                var item = monkey.items.removeFirst()
                item = monkey.operation(item) / 3
                if (monkey.test(item)) {
                    monkeys[monkey.targetOnTrue].items.add(item)
                } else {
                    monkeys[monkey.targetOnFalse].items.add(item)
                }
            }
        }
    }

    val sortedMonkeys = monkeys.sortedBy { -it.passes }
    println(sortedMonkeys[0].passes * sortedMonkeys[1].passes)
}

fun d11t2(lines: List<String>) {
    val monkeys = lines.chunked(7).map { Monkey(it) }
    val commonDivisor: Long = monkeys.map { it.divisor }.reduce { a, b -> a * b}

    repeat(10000) {
        monkeys.forEach { monkey ->
            while(monkey.items.size > 0) {
                monkey.passes++
                var item = monkey.items.removeFirst()
                item = monkey.operation(item)
                item %= commonDivisor
                if (monkey.test(item)) {
                    monkeys[monkey.targetOnTrue].items.add(item)
                } else {
                    monkeys[monkey.targetOnFalse].items.add(item)
                }
            }
        }
    }

    val sortedMonkeys = monkeys.sortedBy { -it.passes }
    println(sortedMonkeys[0].passes * sortedMonkeys[1].passes)
}