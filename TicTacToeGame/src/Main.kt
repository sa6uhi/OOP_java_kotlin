class TicTacToeGame {
    private val board = List(3) { MutableList<Char>(3) { ' ' } }
    private var gameFinished = false
    private var playerOrder = 1

    fun startGame() {
        printBoard()
        makeMove()
    }

    private fun printBoard() {
        println(
            """
            ---------
            | ${board[0].joinToString(" ")} |
            | ${board[1].joinToString(" ")} |
            | ${board[2].joinToString(" ")} |
            ---------
        """.trimIndent()
        )
    }

    private fun makeMove() {
        val input = readln()
        try {
            val (idx1, idx2) = input.trim().split(" ").map { it.toInt() - 1 }
            when {
                board[idx1][idx2] == ('X') || board[idx1][idx2] == ('O') -> {
                    println("This cell is occupied! Choose another one!")
                    makeMove()
                }
                else -> {
                    if (playerOrder == 1) {
                        board[idx1][idx2] = ('X')
                        --playerOrder
                    } else if (playerOrder == 0) {
                        board[idx1][idx2] = ('O')
                        ++playerOrder
                    }
                    printBoard()
                    checkWinner()
                    if (!gameFinished) {
                        makeMove()
                    }
                }
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
            makeMove()
        } catch (e: IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
            makeMove()
        }

    }

    private fun checkWinner() {
        val s = board
        val winLines = "${s[0][0]}${s[0][1]}${s[0][2]} ${s[1][0]}${s[1][1]}${s[1][2]} ${s[2][0]}${s[2][1]}${s[2][2]} " + // horizontal
                "${s[0][0]}${s[1][0]}${s[2][0]} ${s[0][1]}${s[1][1]}${s[2][1]} ${s[0][2]}${s[1][2]}${s[2][2]} " +  // vertical
                "${s[0][0]}${s[1][1]}${s[2][2]} ${s[0][2]}${s[1][1]}${s[2][0]}"  //diagonal
        if (winLines.contains("XXX")) {
            println("X wins")
            gameFinished = true
        } else if (winLines.contains("OOO")) {
            println("O wins")
            gameFinished = true
        } else if (board.all { row -> row.all { it != ' ' } }) {
            println("Draw")
            gameFinished = true
        }


    }
}

fun main() {
    val ticTacToeGame = TicTacToeGame()
    ticTacToeGame.startGame()
}