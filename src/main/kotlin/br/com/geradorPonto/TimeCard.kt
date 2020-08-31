package br.com.geradorPonto

import br.com.geradorPonto.Constants.LOCALE_PT_BR
import br.com.geradorPonto.Constants.SEPARATOR_SEMI_COLON
import java.io.FileWriter
import java.io.IOException
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle

object TimeCard {

  fun generateTimeCard(startDate: LocalDate, endDate: LocalDate) {
    val times = generateTimes(startDate, endDate)
    println("Generated times. \nCreating file..")
    val fileWriter = FileWriter("cartao-ponto-${Constants.MONTHS[Constants.CURRENT_MONTH]}.csv")

    try {
      println("Writing file..")
      fileWriter.appendLine(buildHeader())

      times.forEach { time ->
        fileWriter.appendWithSeparator(time.data.defaultFormat())

        when {
          time.isFeriado ->
            fileWriter.appendWithSeparator("Feriado")
              .appendWithSeparator("Feriado")
              .appendWithSeparator("Feriado")
              .appendLine("Feriado")
          time.isFimDeSemana ->
            time.data.dayOfWeek.getDisplayName(TextStyle.FULL, LOCALE_PT_BR).let { dayOfWeekend ->
              fileWriter.appendWithSeparator(dayOfWeekend)
                .appendWithSeparator(dayOfWeekend)
                .appendWithSeparator(dayOfWeekend)
                .appendLine(dayOfWeekend)
            }
          else ->
            fileWriter.appendWithSeparator(time.horarioEntrada!!.defaultFormat())
              .appendWithSeparator(time.entradaIntervalo!!.defaultFormat())
              .appendWithSeparator(time.saidaIntervalo!!.defaultFormat())
              .appendLine(time.horarioSaida!!.defaultFormat())
        }
      }

      println("Finished write.")
    } catch (ex: Exception) {
      println("Error when has been to write CSV file.")
      ex.printStackTrace()
    } finally {
      try {
        fileWriter.flush()
        fileWriter.close()
        println("Flushed/Closed file.")
      } catch (ex: IOException) {
        println("Error when has been to execute flush or close.")
        ex.printStackTrace()
      }
    }
  }

  private fun generateTimes(inicio: LocalDate, fim: LocalDate): List<Time> = run {
    println("Generating times.. ")

    generateSequence(inicio) { if (it.isBefore(fim)) it.plusDays(1) else null }
      .map {
        val isWeekend = it.dayOfWeek == DayOfWeek.SATURDAY || it.dayOfWeek == DayOfWeek.SUNDAY
        val isHoliday = it.isHoliday()

        if (isWeekend || isHoliday) {
          Time(data = it, isFimDeSemana = isWeekend, isFeriado = isHoliday)
        } else {
          Time(
            data = it,
            horarioEntrada = it.generateTime(Period.START),
            entradaIntervalo = it.generateTime(Period.START_LUNCH),
            saidaIntervalo = it.generateTime(Period.END_LUNCH),
            horarioSaida = it.generateTime(Period.END)
          )
        }
      }.toList()
  }

  private fun buildHeader(): String = run {
    Time::class.java.declaredFields.filter { it.isAnnotationPresent(CsvProperties::class.java) }.joinToString(SEPARATOR_SEMI_COLON) {
      it.getAnnotation(CsvProperties::class.java).header
    }
  }
}

fun main() {
  TimeCard.generateTimeCard(Constants.START_DATE, Constants.END_END)
}