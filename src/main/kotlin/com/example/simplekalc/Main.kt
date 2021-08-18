package com.example.simplekalc

import com.example.simplekalc.eval.ExprBigCalc
import com.example.simplekalc.lexer.Lexer
import com.example.simplekalc.parser.Parser

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        runPrompt()
    } else {
        args.forEach { runExpr(it) }
    }
}

fun runPrompt() {
    while (true) {
        print("> ")
        val line = readLine()
        if (line.isNullOrBlank()) {
            break
        }
        runExpr(line)
    }
}

fun runExpr(expr: String) {
    val parser = Parser(Lexer())
    val result = parser.parse(expr)
    val value = ExprBigCalc().calc(result)
    println(value)
}