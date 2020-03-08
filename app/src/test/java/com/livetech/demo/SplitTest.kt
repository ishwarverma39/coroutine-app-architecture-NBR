package com.livetech.demo

import org.junit.Test
import java.util.*
import kotlin.collections.HashMap


class SplitTest {
    @Test
    fun splitTheString() {
        val text = " <meta charset=\"UTF-8\">\n" +
                "                <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "                <title>Life as an Android Engineer - Truecaller Blog</title>\n" +
                "                <link rel=\"profile\" href=\"http://gmpg.org/xfn/11\">\n" +
                "                <link rel=\"pingback\" href=\"https://truecaller.blog/xmlrpc.php\">"

        val delim = " \n\r\t" //insert here all delimitators

        val st = StringTokenizer(text, delim)
        val arrayMapCount = HashMap<String, Int>()
        while (st.hasMoreTokens()) {
            val text = st.nextToken()
            var count = arrayMapCount[text]
            if (count == null) arrayMapCount[text] = 1 else arrayMapCount[text] = ++count
        }
        arrayMapCount.forEach { println(it.key + " " + it.value) }
    }

    @Test
    fun splitEveryChar() {
        val text = " <meta charset=\"UTF-8\">\n" +
                "                <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "                <title>Life as an Android Engineer - Truecaller Blog</title>\n" +
                "                <link rel=\"profile\" href=\"http://gmpg.org/xfn/11\">\n" +
                "                <link rel=\"pingback\" href=\"https://truecaller.blog/xmlrpc.php\">"
        var pos = 10
        val chars = Array(text.length / pos, { ' ' })
        var index = 0;
        while (pos < text.length) {
            chars[index] = text[pos]
            pos += 10
            index++
        }
        println(Arrays.toString(chars))
        //splitStringEvery(text, 10)?.forEach { if (!it.isNullOrEmpty()) println(it[0]) }
    }

    fun splitStringEvery(s: String, interval: Int): Array<String?>? {
        val arrayLength = Math.ceil(s.length / interval.toDouble()).toInt()
        val result = arrayOfNulls<String>(arrayLength)
        var j = 0
        val lastIndex = result.size - 1
        for (i in 0 until lastIndex) {
            result[i] = s.substring(j, j + interval)
            j += interval
        } //Add the last bit
        result[lastIndex] = s.substring(j)
        return result
    }
}