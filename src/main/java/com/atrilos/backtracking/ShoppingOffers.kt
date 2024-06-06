package com.atrilos.backtracking

/**
 * [638](https://leetcode.com/problems/shopping-offers/description/)
 */
class ShoppingOffers {
    fun shoppingOffers(price: List<Int>, special: List<List<Int>>, needs: List<Int>): Int {
        return dfs(needs, price, special, mutableMapOf())
    }

    fun dfs(
        needs: List<Int>,
        price: List<Int>,
        special: List<List<Int>>,
        cache: MutableMap<List<Int>, Int>
    ): Int {
        if (needs in cache) {
            return cache[needs]!!
        }

        var minimumPrice = needs.mapIndexed { index, itemCount -> itemCount * price[index] }
            .sum()

        for (offer in special) {
            val newNeeds = mutableListOf<Int>()
            for ((index, itemCount) in offer.withIndex()) {
                if (index == offer.size - 1) {
                    continue
                }

                val newNeed = needs[index] - itemCount
                if (newNeed >= 0) {
                    newNeeds.add(newNeed)
                } else {
                    break
                }
            }

            if (newNeeds.size == needs.size) {
                val newPrice = dfs(newNeeds, price, special, cache)
                minimumPrice = minOf(minimumPrice, newPrice + offer.last())
            }
        }

        cache[needs] = minimumPrice
        return minimumPrice
    }
}