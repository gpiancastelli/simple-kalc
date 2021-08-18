package com.example.simplekalc.eval

import com.example.simplekalc.parser.*
import com.example.simplekalc.parser.Number

class ExprPrinter : Visitor<String> {
    fun print(expr: Expr): String = expr.accept(this)

    override fun visitBinary(expr: Binary): String {
        return parenthesize(expr.operator.lexeme, expr.lhs, expr.rhs)
    }

    override fun visitNumber(expr: Number): String {
        return expr.number.lexeme
    }

    override fun visitGrouping(expr: Grouping): String {
        return parenthesize("group", expr.expr)
    }

    private fun parenthesize(name: String, vararg expressions: Expr): String {
        val builder = StringBuilder()
        builder.append("(").append(name)
        for (expr in expressions) {
            builder.append(" ").append(expr.accept(this))
        }
        builder.append(")")
        return builder.toString()
    }
}