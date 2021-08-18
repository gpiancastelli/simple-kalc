package com.example.simplekalc.eval

import com.example.simplekalc.lexer.TokenType
import com.example.simplekalc.parser.*
import com.example.simplekalc.parser.Number

class ExprCalc : Visitor<Double> {
    fun calc(expr: Expr): Double = expr.accept(this)

    override fun visitBinary(expr: Binary): Double {
        val lhs = expr.lhs.accept(this)
        val rhs = expr.rhs.accept(this)
        return when (expr.operator.type) {
            TokenType.PLUS -> lhs + rhs
            TokenType.MINUS -> lhs - rhs
            TokenType.STAR -> lhs * rhs
            TokenType.SLASH -> lhs / rhs
            else -> throw IllegalArgumentException()
        }
    }

    override fun visitNumber(expr: Number): Double {
        return expr.number.lexeme.toDouble()
    }

    override fun visitGrouping(expr: Grouping): Double {
        return expr.expr.accept(this)
    }
}