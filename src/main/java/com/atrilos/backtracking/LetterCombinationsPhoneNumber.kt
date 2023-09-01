package com.atrilos.backtracking

private val MAPPING = arrayOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty())
        return emptyList()

    val result = mutableListOf<String>()

    dfs(StringBuilder(), result, digits, 0)

    return result
}

fun dfs(currentString: StringBuilder, result: MutableList<String>, digits: String, pos: Int) {
    if (currentString.length == digits.length) {
        result += currentString.toString()
        return
    }
    val digit = digits[pos] - '0'
    for (letter in MAPPING[digit]) {
        currentString.append(letter)
        dfs(currentString, result, digits, pos + 1)
        currentString.setLength(currentString.length - 1)
    }
}
