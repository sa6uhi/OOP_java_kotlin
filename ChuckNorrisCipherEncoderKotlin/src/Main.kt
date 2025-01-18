class ChuckNorrisCipherEncoder {

    fun start() {
        while (true) {
            println("Please input operation (encode/decode/exit):")
            when (val choice = readln()) {
                "encode" -> chuckNorrisCipherEncoder()
                "decode" -> chuckNorrisCipherDecoder()
                "exit" -> {
                    println("Bye!")
                    break
                }
                else -> println("There is no '$choice' operation")
            }
        }
    }

    private fun chuckNorrisCipherDecoder() {

        println("Input encoded string:")
        val encodedString = readln()
        val list = encodedString.split(" ")

        if (!encodedString.all { it == '0' || it == ' ' } && list.size % 2 != 0) {
            println("Encoded string is not valid.")
            return
        }

        val binaryStr = StringBuilder()
        for (i in list.indices step 2) {
            if (list[i] == "0" || list[i] == "00") {
                when {
                    list[i] == "0" -> binaryStr.append("1".repeat(list[i + 1].length))
                    list[i] == "00" -> binaryStr.append("0".repeat(list[i + 1].length))
                }
            } else {
                println("Encoded string is not valid.")
                return
            }
        }

        if (binaryStr.length % 7 == 0) {
            val decodedString = binaryStr.chunked(7)
                .map { it.toInt(2).toChar() }
                .joinToString("")

            println("Decoded string:")
            println(decodedString)
        } else {
            println("Encoded string is not valid.")
        }
    }

    private fun chuckNorrisCipherEncoder() {

        println("Input string:")
        val string = readln().toList().joinToString("")

        val binaryString = StringBuilder()
        for (ch in string) {
            val binaryCh = ch.code.toString(2).padStart(7, '0')
            binaryString.append(binaryCh)
        }

        val matches = Regex("(0+|1+)").findAll(binaryString)
        val encodedString = StringBuilder()
        for (match in matches) {
            val matchValue = match.value
            if (matchValue.startsWith("0")) {
                encodedString.append("00").append(" ")
            } else {
                encodedString.append("0").append(" ")
            }
            encodedString.append("0".repeat(matchValue.length)).append(" ")
        }

        println("Encoded string:")
        println(encodedString.toString().trim())

    }
}

fun main() {
    val app = ChuckNorrisCipherEncoder()
    app.start()
}
