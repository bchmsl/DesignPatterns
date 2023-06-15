package com.space.designpatterns.behavioral

/** [Printer] class that uses strategy pattern as an interface */
class Printer(private val stringFormattingStrategy: StringFormattingStrategy){
    fun print(message:String){
        println(stringFormattingStrategy(message))
    }
}

/** Strategy pattern abstraction and implementations */
interface StringFormattingStrategy{
    operator fun invoke(string: String): String
}

class UpperCaseFormattingStrategy: StringFormattingStrategy{
    override fun invoke(string: String): String {
        return string.uppercase()
    }
}

class LowerCaseFormattingStrategy: StringFormattingStrategy{
    override fun invoke(string: String): String {
        return string.lowercase()
    }
}

fun main() {
    val message = "THIS text is TO BE printed"

    val upperCasePrinter = Printer(UpperCaseFormattingStrategy())
    upperCasePrinter.print(message)

    val lowerCasePrinter = Printer(LowerCaseFormattingStrategy())
    lowerCasePrinter.print(message)
}

