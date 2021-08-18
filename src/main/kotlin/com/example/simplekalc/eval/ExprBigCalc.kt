package com.example.simplekalc.eval

import com.example.simplekalc.lexer.TokenType
import com.example.simplekalc.parser.*
import com.example.simplekalc.parser.Number
import java.math.BigDecimal

class ExprBigCalc : Visitor<BigDecimal> {
    fun calc(expr: Expr): BigDecimal = expr.accept(this)

    override fun visitBinary(expr: Binary): BigDecimal {
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

    override fun visitNumber(expr: Number): BigDecimal {
        return BigDecimal(expr.number.lexeme)
    }

    override fun visitGrouping(expr: Grouping): BigDecimal {
        return expr.expr.accept(this)
    }
}