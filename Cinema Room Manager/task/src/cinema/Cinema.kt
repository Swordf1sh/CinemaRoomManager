package cinema

import java.lang.IndexOutOfBoundsException

var SEATS = 0
var INCOME = 0
var SOLD = 0

fun calculatePercentage(): String = "%.2f".format(SOLD * 100 / SEATS.toFloat())

fun calculateTotalIncome(rows: Int, seatsInRow: Int): Int {
    val totalSeats = rows * seatsInRow
    if (totalSeats <= 60) return totalSeats * 10
    return (rows / 2 * 10) * seatsInRow + ((rows - rows / 2) * 8) * seatsInRow
}

fun calculateTicketPrice(rows: Int, seatsInRow: Int, row: Int): Int {
    val totalSeats = rows * seatsInRow
    if (totalSeats <= 60) return 10
    if (row < rows / 2) return 10
    return 8
}

fun printCinema(seat: MutableList<MutableList<String>>) {
    println("Cinema:")
    (1..seat[0].size).forEach { print(" $it") }
    (0 until seat.size).forEach {it ->
        print("\n${it+1}")
        seat[it].forEach { i -> print(" $i") }
    }
    println()
}

fun buyTicket(seats: MutableList<MutableList<String>>, rows: Int, seatsInRow: Int) {
    while (true) {
        println("Enter a row number:")
        val r = readln().toInt() - 1
        println("Enter a seat number in that row:")
        val s = readln().toInt() - 1
        try {
            when (seats[r][s]) {
                "B" -> println("That ticket has already been purchased!")
                "S" -> {
                    seats[r][s] = "B"
                    val price = calculateTicketPrice(rows, seatsInRow, r)
                    INCOME += price
                    SOLD ++
                    println("Ticket price: $${price}")
                    break
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            println("Wrong input!")
        }
    }
}

fun showStatistic(rows: Int, seatsInRow: Int) {
    println("Number of purchased tickets: $SOLD")
    println("Percentage: ${calculatePercentage()}%")
    println("Current income: $$INCOME")
    println("Total income: $${calculateTotalIncome(rows, seatsInRow)}")
}

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRow = readln().toInt()
    SEATS = rows * seatsInRow
    val seats = MutableList(rows) { MutableList(seatsInRow) { "S" } }

    while (true) {
        println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
        when (readln().toInt()) {
            1 -> printCinema(seats)
            2 -> buyTicket(seats, rows, seatsInRow)
            3 -> showStatistic(rows, seatsInRow)
            0 -> break
        }
    }
}
