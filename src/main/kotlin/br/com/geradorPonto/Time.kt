package br.com.geradorPonto

import java.time.LocalDate
import java.time.LocalTime

data class Time(
  @CsvProperties(header = "Data")
  val data: LocalDate,
  @CsvProperties(header = "Horário de Entrada")
  val horarioEntrada: LocalTime? = null,
  @CsvProperties(header = "Entrada do Intervalo")
  val entradaIntervalo: LocalTime? = null,
  @CsvProperties(header = "Saída do Intervalo")
  val saidaIntervalo: LocalTime? = null,
  @CsvProperties(header = "Horário de Saída")
  val horarioSaida: LocalTime? = null,
  val isFimDeSemana: Boolean = false,
  val isFeriado: Boolean = false
) {

  override fun toString(): String {
    return "${this::class.simpleName}(" +
      "${this::data.name}=$data, " +
      horarioEntrada.orEmptyString { "${this::horarioEntrada.name}=${it.defaultFormat()}, " } +
      entradaIntervalo.orEmptyString { " ${this::entradaIntervalo.name}=${it.defaultFormat()}, " } +
      saidaIntervalo.orEmptyString { "${this::saidaIntervalo.name}=${it.defaultFormat()}, " } +
      horarioSaida.orEmptyString { "${this::horarioSaida.name}=${it.defaultFormat()}, " } +
      "${this::isFimDeSemana.name}=$isFimDeSemana, " +
      "${this::isFeriado.name}=$isFeriado" +
      ")"
  }
}