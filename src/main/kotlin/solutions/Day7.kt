package solutions

// I cheated a little and added "$ cd .." as the last line in the input to remove it as edge case :D
fun d7t1(lines: List<String>) {
    var sum = 0
    val folders = ArrayDeque<Int>()

    for (line in lines) {
        val op = line.split(" ")

        if (op[0] == "$") {
            if (op[1] == "cd" && op[2] == "..") {
                val lastFolder = folders.removeLast()
                if (lastFolder <= 100000) {
                    sum += lastFolder
                }
                folders.replaceAll { it + lastFolder }
            } else if (op[1] == "cd") {
                folders.add(0)
            }
        } else if (op[0] != "dir") {
            folders.add(folders.removeLast() + op[0].toInt())
        }
    }

    println(sum)
}

fun d7t2(lines: List<String>) {
    var totalSpaceUsed = 0
    val foldersList = mutableListOf<Int>()
    val folders = ArrayDeque<Int>()

    for (line in lines) {
        val op = line.split(" ")

        if (op[0] == "$") {
            if (op[1] == "cd" && op[2] == "..") {
                foldersList.add(folders.removeLast())
            } else if (op[1] == "cd") {
                folders.add(0)
            }
        } else if (op[0] != "dir") {
            val value = op[0].toInt()
            totalSpaceUsed += value
            folders.replaceAll { it + value }
            folders.add(folders.removeLast() + value)
        }
    }
    val find = foldersList.sorted().find { it > 30000000 - (70000000 - totalSpaceUsed) }
    println(find)
}