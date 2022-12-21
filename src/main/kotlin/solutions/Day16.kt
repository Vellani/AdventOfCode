package solutions

/*
Looks difficult but in reality it is a basic X by X matrix
 */

data class Pipe(
    val pipe: String,
    val pressure: Int,
    val tunnels: HashSet<PipeConnection>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pipe

        if (pipe != other.pipe) return false

        return true
    }

    override fun hashCode(): Int {
        return pipe.hashCode()
    }
}

data class PipeConnection(
    val pipe: String,
    val requiredTime: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PipeConnection

        if (pipe != other.pipe) return false

        return true
    }

    override fun hashCode(): Int {
        return pipe.hashCode()
    }
}

fun inputParse(input: String): List<String> {
    return input.replace("Valve ", "")
        .replace(" has flow rate=", " ")
        .replace("; tunnels lead to valves ", " ")
        .replace("; tunnel leads to valve ", " ")
        .split(" ", ", ")
}

fun buildInitialMap(lines: List<String>): HashSet<Pipe> {
    val pipes: HashSet<Pipe> = hashSetOf()
    lines.forEach {
        val split = inputParse(it)
        val pipe =
            Pipe(split[0], split[1].toInt(), HashSet(split.subList(2, split.size).map { e -> PipeConnection(e, 1) }))
        pipe.tunnels.add(PipeConnection(split[0], 0))
        pipes.add(pipe)
    }
    return populateMovementMap(pipes)
}

fun populateMovementMap(pipes: HashSet<Pipe>): HashSet<Pipe> {
    val newPipes = HashSet<Pipe>()

    pipes.forEach {
        val newPipe = it.copy(tunnels = HashSet(it.tunnels))
        newPipes.add(newPipe)
    }

    for (pipe in pipes) {
        val missingConnections = pipes.map { it.pipe }.subtract(pipe.tunnels.map { it.pipe }.toSet())
        missingConnections.forEach { targetPipe ->

            val visited = HashSet(pipe.tunnels)
            var counter = 1
            while (!visited.map { it.pipe }.contains(targetPipe)) {
                counter++
                val newTunnels = hashSetOf<PipeConnection>()
                for (j in visited) {
                    newTunnels.addAll(pipes.find { it.pipe == j.pipe }!!.tunnels)
                    if (newTunnels.map { it.pipe }.contains(targetPipe)) {
                        newPipes.find { it == pipe }!!.tunnels.add(PipeConnection(targetPipe, counter))
                        break
                    }
                }
                visited.addAll(newTunnels)
            }
        }
    }
    return newPipes
}

fun d16t1(lines: List<String>) {
    val pipeMap = buildInitialMap(lines)
    val pressure = nodePathfinder(pipeMap.find { it.pipe == "AA" }!!, HashSet(), 30, pipeMap)
    println(pressure)
}

fun nodePathfinder(pipe: Pipe, visited: HashSet<Pipe>, minutes: Int, pipeMap: HashSet<Pipe>): Int {
    visited.add(pipe)

    val options = pipeMap.find { it == pipe }!!
        .tunnels
        .filter { !visited.map { e -> e.pipe }.contains(it.pipe) && (it.requiredTime + 1) <= minutes }
        .map { e -> pipeMap.find { it.pipe == e.pipe }!! to e.requiredTime }
        .filter { it.first.pressure > 0 }
        .map { e ->
            nodePathfinder(e.first, visited, minutes - (e.second + 1), pipeMap)
        }
    visited.remove(pipe)
    return (options.maxByOrNull { it } ?: 0) + (pipe.pressure * minutes)
}


/* _------------------------------
        NOT WORKING ATM
 */
fun d16t2(lines: List<String>) {
    val pipeMap = buildInitialMap(lines)

    val visited = HashSet<Pipe>()
    val minutes = 26
    val playerPos = pipeMap.find { it.pipe == "AA" }!!
    val elephantPos = pipeMap.find { it.pipe == "AA" }!!


    val listOfTopTwoPl = nodePathfinderDuo(playerPos, visited, minutes, pipeMap)
    val listOfTopTwoEl = nodePathfinderDuo(elephantPos, visited, minutes, pipeMap)


}

fun nodePathfinderDuo(pipe: Pipe, visited: HashSet<Pipe>, minutes: Int, pipeMap: HashSet<Pipe>): List<PipeSolution> {
    visited.add(pipe)

    val options = pipeMap.find { it == pipe }!!
        .tunnels
        .filter { !visited.map { e -> e.pipe }.contains(it.pipe) && (it.requiredTime + 1) <= minutes }
        .mapNotNull { e ->
            val newPipe = pipeMap.find { it.pipe == e.pipe }!!
            if (newPipe.pressure > 0) {
                PipeSolution(
                    newPipe,
                    (pipe.pressure * minutes) + nodePathfinder(newPipe, visited, minutes - (e.requiredTime + 1), pipeMap),
                    e.requiredTime + 1,
                )
            } else {
                null
            }
        }.sortedBy { -it.value }

    visited.remove(pipe)

    return listOf(options[0], options[1])
}

data class PipeSolution(
    val pipe: Pipe,
    val value: Int,
    val timeUsed: Int
)