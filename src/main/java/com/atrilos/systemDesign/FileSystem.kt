package com.atrilos.systemDesign

/**
 * [588](https://leetcode.com/problems/design-in-memory-file-system/description/)
 */
class FileSystem {

    data class File(val name: String,
                    val isFile: Boolean = false,
                    var content: String = "",
                    val children: MutableMap<String, File> = mutableMapOf())

    val root = File("")
    fun ls(path: String): List<String> {
        if (path == "/") {
            return root.children.keys.sorted()
        }
        val pathSplit = path.split("/")
        var currentFile = root
        for (i in 1..pathSplit.lastIndex) {
            val nextDir = pathSplit[i]
            currentFile = currentFile.children[nextDir]!!
        }
        return if (currentFile.isFile) {
            listOf(currentFile.name)
        } else {
            currentFile.children.keys.sorted()
        }
    }

    fun mkdir(path: String) {
        val pathSplit = path.split("/")
        var currentDir = root
        for (i in 1..pathSplit.lastIndex) {
            val nextDir = pathSplit[i]
            currentDir = currentDir.children.getOrPut(nextDir) { File(nextDir) }
        }
    }

    fun addContentToFile(filePath: String, content: String) {
        val pathSplit = filePath.split("/")
        var currentFile = root
        for (i in 1..<pathSplit.lastIndex) {
            val nextDir = pathSplit[i]
            currentFile = currentFile.children[nextDir]!!
        }
        val fileName = pathSplit.last()
        if (fileName in currentFile.children) {
            currentFile = currentFile.children[fileName]!!
        } else {
            currentFile.children[fileName] = File(fileName, true)
            currentFile = currentFile.children[fileName]!!
        }
        currentFile.content += content
    }

    fun readContentFromFile(filePath: String): String {
        val pathSplit = filePath.split("/")
        var currentFile = root
        for (i in 1..pathSplit.lastIndex) {
            val nextDir = pathSplit[i]
            currentFile = currentFile.children[nextDir]!!
        }
        return currentFile.content
    }

}