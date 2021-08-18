package com.example.simplekalc.parser

import com.example.simplekalc.lexer.Token

interface Expr {
    fun <T> accept(visitor: Visitor<T>): T
}

interface Visitor<T> {
    fun visitBinary(expr: Binary): T
    fun visitNumber(expr: Number): T
    fun visitGrouping(expr: Grouping): T
}

data class Binary(
    val lhs: Expr,
    val operator: Token,
    val rhs: Expr
) : Expr {
    override fun <T> accept(visitor: Visitor<T>): T =
        visitor.visitBinary(this)
}

data class Number(
    val number: Token
) : Expr {
    override fun <T> accept(visitor: Visitor<T>): T =
        visitor.visitNumber(this)
}

data class Grouping(
    val expr: Expr
) : Expr {
    override fun <T> accept(visitor: Visitor<T>): T =
        visitor.visitGrouping(this)
}