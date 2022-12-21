import solutions.*
import java.io.File

const val year = 2022
const val day = 16
const val task = 2
const val runOnTest = false

fun readFromFile(): List<String> {
    val path = if (runOnTest) "src/main/resources/$year/input/test/$day.txt" else "src/main/resources/$year/input/personal/$day.txt"
    return File(path).bufferedReader().readLines()
}

fun main() {
    solve()
}

fun solve() {
    val lines = readFromFile()
    when (year to day) {
        2022 to 1 -> { if(task == 1) d1t1(lines) else d1t2(lines) }
        2022 to 2 -> { if(task == 1) d2t1(lines) else d2t2(lines) }
        2022 to 3 -> { if(task == 1) d3t1(lines) else d3t2(lines) }
        2022 to 4 -> { if(task == 1) d4t1(lines) else d4t2(lines) }
        2022 to 5 -> { if(task == 1) d5t1(lines) else d5t2(lines) }
        2022 to 6 -> { if(task == 1) d6t1(lines) else d6t2(lines) }
        2022 to 7 -> { if(task == 1) d7t1(lines) else d7t2(lines) }
        2022 to 8 -> { if(task == 1) d8t1(lines) else d8t2(lines) }
        2022 to 9 -> { if(task == 1) d9t1(lines) else d9t2(lines) }
        2022 to 10 -> { if(task == 1) d10t1(lines) else d10t2(lines) }
        2022 to 11 -> { if(task == 1) d11t1(lines) else d11t2(lines) }
        2022 to 12 -> { if(task == 1) d12t1(lines) else d12t2(lines) }
        2022 to 13 -> { if(task == 1) d13t1(lines) else d13t2(lines) }
        2022 to 14 -> { if(task == 1) d14t1(lines) else d14t2(lines) }
        2022 to 15 -> { if(task == 1) d15t1(lines) else d15t2(lines) }
        2022 to 16 -> { if(task == 1) d16t1(lines) else d16t2(lines) }
    }

    if (runOnTest) {
        printTestCorrectTestAnswer()
    }
}

fun printTestCorrectTestAnswer() {
    println("------------------------------------------------------------")
    println("Correct answer is: " + when (year to day) {
        2022 to 1 -> { if(task == 1) "24000" else "45000" }
        2022 to 2 -> { if(task == 1) "15" else "12" }
        2022 to 3 -> { if(task == 1) "157" else "70" }
        2022 to 4 -> { if(task == 1) "2" else "4" }
        2022 to 5 -> { if(task == 1) "CMZ" else "MCD" }
        2022 to 6 -> { if(task == 1) "10" else "29" }
        2022 to 7 -> { if(task == 1) "95437" else "24933642" }
        2022 to 8 -> { if(task == 1) "21" else "8" }
        2022 to 9 -> { if(task == 1) "13" else "36" }
        2022 to 10 -> { if(task == 1) "13140" else "NOTHING TO SHOW" }
        2022 to 11 -> { if(task == 1) "10605" else "2713310158" }
        2022 to 12 -> { if(task == 1) "31" else "29" }
        2022 to 13 -> { if(task == 1) "13" else "140" }
        2022 to 14 -> { if(task == 1) "24" else "93" }
        2022 to 15 -> { if(task == 1) "26" else "56000011" }
        2022 to 16 -> { if(task == 1) "1651" else "1707" }
        else -> { "Unknown"}
    })
    println("------------------------------------------------------------")
}

fun writeToFile(input: Array<Array<Char>>) {
    val sb = StringBuffer()
    input.forEach {
        it.forEach { e ->
            sb.append(e)
        }
        sb.append(System.lineSeparator())
    }
    writeToFile(sb.toString())
}

fun writeToFile(input: ArrayList<ArrayList<Char>>) {
    val sb = StringBuffer()
    input.forEach {
        it.forEach { e ->
            sb.append(e)
        }
        sb.append(System.lineSeparator())
    }
    writeToFile(sb.toString())
}

fun writeToFile(input: String) {
    val folderDir = File("src/main/resources/$year/output")
    val f = File(folderDir, "$day.txt")
    f.parentFile.mkdirs()
    f.writeBytes(input.toByteArray())
}