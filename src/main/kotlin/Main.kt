
import solutions.*
import java.io.File

fun readFromFile(year: Int, day: Int): List<String> {
    val path = "src/main/resources/$year/input/$day.txt"
    return File(path).bufferedReader().readLines()
}

fun print(any: Any) {
    println(any.toString())
}

fun main(args: Array<String>) {
    val year = 2022
    val day = 1
    val lines = readFromFile(year, day)

    d1t1(lines)
}

