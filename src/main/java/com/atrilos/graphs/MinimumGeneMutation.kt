package com.atrilos.graphs

import java.util.*

/**
 * [433](https://leetcode.com/problems/minimum-genetic-mutation/description/)
 */
fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
    val bankSet = mutableSetOf<String>().apply { addAll(bank.toList()) }
    val queue = LinkedList<Pair<String, Int>>().apply { push(startGene to 0)}
    while (queue.isNotEmpty()) {
        val (gene, steps) = queue.pop()
        if (gene == endGene) return steps
        val used = mutableSetOf<String>()
        for (possibleMutation in bankSet) {
            var diff = 0
            for (i in endGene.indices) {
                if (possibleMutation[i] != gene[i]) diff++
            }
            if (diff == 1) {
                queue.push(possibleMutation to steps + 1)
                used += possibleMutation
            }
        }
        bankSet.removeAll(used)
    }

    return -1
}