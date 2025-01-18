package cinema

var soldTickets = 0
var currentIncome = 0
var totalIncome = 0

fun main() {

    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val columns = readln().toInt()
    val seats = rows * columns

    val cinema = MutableList(rows + 1) { MutableList(columns + 1) { ' ' } }
    createCinema(rows, columns, cinema)



    do {
        println()
        println(
            "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )
        val choice = readln().toInt()
        when (choice) {
            1 -> showTheSeats(cinema)
            2 -> buyATicket(rows, columns, cinema)
            3 -> statistics(soldTickets, currentIncome, seats, rows, columns)
            else -> return
        }
    } while (choice != 0)

}

fun createCinema(rows: Int, columns: Int, cinema: MutableList<MutableList<Char>>) {
    for (col in 1..columns) {
        cinema[0][col] = col.toString()[0]
    }
    for (row in 1..rows) {
        cinema[row][0] = row.toString()[0]
        for (col in 1..columns) {
            cinema[row][col] = 'S'
        }
    }
}

fun showTheSeats(cinema: MutableList<MutableList<Char>>) {

    println("Cinema:")
    for (row in cinema) {
        println(row.joinToString(" "))
    }
}

fun buyATicket(rows: Int, columns: Int, cinema: MutableList<MutableList<Char>>) {

    println("Enter a row number:")
    val rowIndex = readln().toInt()
    println("Enter a seat number in that row:")
    val columnIndex = readln().toInt()

    try {
        if (cinema[rowIndex][columnIndex] != 'B') {
            var ticketPrice = 10

            if (rows * columns <= 60 || rows / 2 > rowIndex) {
                println("Ticket price: \$$ticketPrice")
            } else if (rows / 2 <= rowIndex) {
                ticketPrice = 8
                println("Ticket price: \$$ticketPrice")
            }

            cinema[rowIndex][columnIndex] = 'B'

            soldTickets++
            currentIncome += ticketPrice

        } else {

            println("\nThat ticket has already been purchased!\n")
            buyATicket(rows, columns, cinema)
        }
    } catch (e: Exception) {
        println("\nWrong input!\n")
        buyATicket(rows, columns, cinema)
    }



}

fun statistics(
    soldTickets: Int,
    currentIncome: Int,
    seats: Int,
    rows: Int,
    columns: Int
) {
    val formatPercentage = "%.2f".format(soldTickets * (100 / seats.toDouble()))
    val totalIncome = if (rows * columns <= 60) {
        rows * columns * 10
    } else {
        (rows / 2 * columns * 10) + ((rows - (rows / 2)) * columns * 8)
    }
    println(
        "Number of purchased tickets: $soldTickets\n" +
                "Percentage: $formatPercentage%\n" +
                "Current income: \$$currentIncome\n" +
                "Total income: \$${totalIncome}"
    )
}