package com.atrilos.stack

class DecodeString {

    private var i = 0

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(DecodeString().decodeString("2[abc]3[cd]ef"))
        }
    }

    fun decodeString(s: String): String {
        var cnt = 0
        val sb = StringBuilder()

        while (i <= s.lastIndex) {
            val ch = s[i++]

            when  {
                ch == '[' -> {
                    val tmp = decodeString(s)
                    sb.append(tmp.repeat(cnt))
                    cnt = 0
                }
                ch == ']' -> break
                ch.isLetter() -> sb.append(ch)
                else -> cnt = cnt * 10 + (ch - '0')
            }
        }

        return sb.toString()
    }
}