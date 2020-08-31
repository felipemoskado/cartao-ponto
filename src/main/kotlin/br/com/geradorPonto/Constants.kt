package br.com.geradorPonto

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

object Constants {
  private val PREVIOUS_MONTH: Int = Calendar.getInstance().get(Calendar.MONTH)
  private val CURRENT_YEAR: Int = Calendar.getInstance().get(Calendar.YEAR)
  private const val START_DAY: Int = 25
  private const val END_DAY: Int = 24

  const val TIME_RANGE_IN_MINUTES: Long = 5L
  const val SEPARATOR_SEMI_COLON: String = ";"

  val LOCALE_PT_BR = Locale("pt", "BR")
  val CURRENT_MONTH: Int = PREVIOUS_MONTH + 1
  val START_DATE: LocalDate = LocalDate.of(CURRENT_YEAR, PREVIOUS_MONTH, START_DAY)
  val END_END: LocalDate = LocalDate.of(CURRENT_YEAR, CURRENT_MONTH, END_DAY)
  val TIMEZONE_SAO_PAULO: ZoneId = ZoneId.of("America/Sao_Paulo")
  val START_TIME: LocalTime = LocalTime.of(8, 0)
  val START_TIME_LUNCH: LocalTime = LocalTime.of(12, 0)
  val END_TIME_LUNCH: LocalTime = LocalTime.of(13, 0)
  val END_TIME: LocalTime = LocalTime.of(18, 0)
  val HOLIDAYS = mapOf(
    LocalDate.of(2020, 1, 1) to "Ano Novo",
    LocalDate.of(2020, 2, 24) to "Carnaval",
    LocalDate.of(2020, 2, 25) to "Carnaval",
    LocalDate.of(2020, 2, 26) to "Carnaval",
    LocalDate.of(2020, 4, 10) to "Sexta-feira Santa",
    LocalDate.of(2020, 4, 21) to "Tiradentes",
    LocalDate.of(2020, 5, 1) to "Dia do Trabalho",
    LocalDate.of(2020, 6, 11) to "Copus Christi",
    LocalDate.of(2020, 9, 7) to "Independência do Brasil",
    LocalDate.of(2020, 9, 8) to "N. Sra da Luz dos Pinhais",
    LocalDate.of(2020, 10, 12) to "Nossa Senhora Aparecida",
    LocalDate.of(2020, 11, 2) to "Dia de Finados",
    LocalDate.of(2020, 11, 15) to "Proclamação da República",
    LocalDate.of(2020, 12, 25) to "Natal"
  )
  val MONTHS = mapOf(
    0 to "Janeiro",
    1 to "Fevereiro",
    2 to "Março",
    3 to "Abril",
    4 to "Maio",
    5 to "Junho",
    6 to "Julho",
    7 to "Agosto",
    8 to "Setembro",
    9 to "Outubro",
    10 to "Novembro",
    11 to "Dezembro",
  )
}

