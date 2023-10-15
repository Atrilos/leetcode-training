package com.atrilos.graphs

/**
 * [1236](https://leetcode.com/problems/web-crawler/description/)
 */
class WebCrawler {
    class HtmlParser {
        fun getUrls(url: String): List<String> {
            TODO()
        }
    }

    private val visited = mutableSetOf<String>()

    fun crawl(startUrl: String, htmlParser: HtmlParser): List<String> {
        if (startUrl in visited) return emptyList()

        visited.add(startUrl)

        return listOf(startUrl) + htmlParser.getUrls(startUrl)
            .filter { it.hostname == startUrl.hostname }
            .flatMap { crawl(it, htmlParser) }
    }

    private val String.hostname: String get() = split("http://")[1].split("/")[0]
}
