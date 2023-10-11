package com.example.fefufit

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.ceil


private fun ask(date: String) {
    System.out.printf("? %s\n", date)
    System.out.flush()
}

private fun print(answer: String) {
    System.out.printf("! %s\n", answer)
    System.out.flush()
}

private fun getValue(scanner: Scanner): Int {
    val sing = scanner.next()

    return if (sing == "?") {
        scanner.nextInt()
    } else {
        return -1
    }
}

private fun setDiapason(startDate:LocalDate, endDate:LocalDate):LocalDate{
    val diff = ChronoUnit.DAYS.between(startDate, endDate)
    return startDate.plusDays(diff / 2)
}


fun main(args: Array<String>) {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    var startDate = LocalDate.of(1970, 1,1)
    var endDate = LocalDate.of(2020,12,31)
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    ask(endDate.format(formatter))
    val dayCount = getValue(scanner)
    val currentDay = ceil(dayCount.toFloat()/2)

    for (i in 1 until n){
        val middleDate = setDiapason(startDate, endDate)
        ask(middleDate.format(formatter))
        val days = getValue(scanner)

        if (days == -1)
            return

        if (days < currentDay)
            startDate = middleDate
        else if (days > currentDay)
            endDate = middleDate
        else{
            print(middleDate.format(formatter))
            return
        }
    }

}

