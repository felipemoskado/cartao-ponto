package br.com.geradorPonto

@Target(AnnotationTarget.FIELD)
@Retention
annotation class CsvProperties(val header: String)