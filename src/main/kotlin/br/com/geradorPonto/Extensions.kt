package br.com.geradorPonto

import java.io.Writer
import java.time.*
import java.time.format.DateTimeFormatter
import kotlin.random.Random

fun <T> T?.orEmptyString(execute: (T) -> String): String = if (this == null) "" else execute(this)

fun LocalDate.generateTime(period: Period): LocalTime =
  when (period) {
    Period.START -> generateTime(Constants.START_TIME).toLocalTime()
    Period.START_LUNCH -> generateTime(Constants.START_TIME_LUNCH).toLocalTime()
    Period.END_LUNCH -> generateTime(Constants.END_TIME_LUNCH).toLocalTime()
    Period.END -> generateTime(Constants.END_TIME).toLocalTime()
  }

private fun LocalDate.generateTime(time: LocalTime): Long = run {
  val minTimeRange: Long = time.minusMinutes(Constants.TIME_RANGE_IN_MINUTES).toZoneDateTime(this).toMilliSeconds()
  val maxTimeRange: Long = time.plusMinutes(Constants.TIME_RANGE_IN_MINUTES).toZoneDateTime(this).toMilliSeconds()

  Random.nextLong(minTimeRange, maxTimeRange)
}

fun LocalTime.defaultFormat(): String = format(DateTimeFormatter.ofPattern("hh:mm"))

fun LocalTime.toZoneDateTime(date: LocalDate, zoneId: ZoneId = Constants.TIMEZONE_SAO_PAULO): ZonedDateTime =
  date.atTime(this).atZone(zoneId)

fun Long.toLocalTime(): LocalTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), Constants.TIMEZONE_SAO_PAULO).toLocalTime()

fun LocalDate.defaultFormat(): String = format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

fun LocalDate.isHoliday(): Boolean = Constants.HOLIDAYS.containsKey(this)

fun ZonedDateTime.toMilliSeconds(): Long = this.toInstant().toEpochMilli()

fun Writer.appendWithSeparator(value: String, separator: String = Constants.SEPARATOR_SEMI_COLON): Writer =
  this.append(value).append(separator)
