import solutions.*
import java.io.File

fun readFromFile(year: Int, day: Int): List<String> {
    val path = "src/main/resources/$year/input/$day.txt"
    return File(path).bufferedReader().readLines()
}

fun main(args: Array<String>) {
    val year = 2022
    val day = 12
    val task = 2

    solve(year, day, task)
}

fun solve(year: Int, day: Int, task: Int) {
    val lines = readFromFile(year, day)
    when (day) {
        1 -> { if(task == 1) d1t1(lines) else d1t2(lines) }
        2 -> { if(task == 1) d2t1(lines) else d2t2(lines) }
        3 -> { if(task == 1) d3t1(lines) else d3t2(lines) }
        4 -> { if(task == 1) d4t1(lines) else d4t2(lines) }
        5 -> { if(task == 1) d5t1(lines) else d5t2(lines) }
        6 -> { if(task == 1) d6t1(lines) else d6t2(lines) }
        7 -> { if(task == 1) d7t1(lines) else d7t2(lines) }
        8 -> { if(task == 1) d8t1(lines) else d8t2(lines) }
        9 -> { if(task == 1) d9t1(lines) else d9t2(lines) }
        10 -> { if(task == 1) d10t1(lines) else d10t2(lines) }
        11 -> { if(task == 1) d11t1(lines) else d11t2(lines) }
        12 -> { if(task == 1) d12t1(lines) else d12t2(lines) }
    }

}