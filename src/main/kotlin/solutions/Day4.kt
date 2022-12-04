package solutions

fun d4t1(lines: List<String>) {
    var sum = 0
    for (line in lines) {
        val split = line
            .split(",")
            .map { it.split("-").map{e -> e.toInt()} }
            .sortedWith(compareBy({it[0]}, {-it[1]}))

        if (split[1][1] <= split[0][1]) sum++
    }
    println(sum)
}

fun d4t2(lines: List<String>) {
    var sum = 0
    for (line in lines) {
        val split = line
            .split(",")
            .map { it.split("-").map{e -> e.toInt()} }
            .sortedWith(compareBy({it[0]}, {-it[1]}))

        if (split[0][1] >= split[1][0]) sum++
    }
    println(sum)
}


// --------------------------------------------------------------------------------------------------------------------
// Solved by reddit used lucianoq
// https://www.reddit.com/r/adventofcode/comments/zc0zta/comment/iyvpb4x/?utm_source=share&utm_medium=web2x&context=3
fun d4Bonus(lines: List<String>) {
    var contained = 0
    var overlapped = 0
    for (line in lines) {
        val i = line.split(",", "-").map { it.toInt() }
        if ( (i[2] - i[0]) * (i[1] - i[3]) >= 0) contained++
        if ( (i[3] - i[0]) * (i[1] - i[2]) >= 0) overlapped++
    }
    println(contained)
    println(overlapped)
}