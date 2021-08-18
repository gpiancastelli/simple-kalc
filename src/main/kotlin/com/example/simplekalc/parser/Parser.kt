package com.example.simplekalc.parser

import com.example.simplekalc.lexer.Lexer
import com.example.simplekalc.lexer.Token
import com.example.simplekalc.lexer.TokenType

class Parser(private val lexer: Lexer) {
    private var tokens = emptyList<Token>()
    private var current = 0

    fun parse(source: String): Expr {
        tokens = lexer.readTokens(source)
        current = 0
        val expr = expr()
        if (isAtEnd()) {
            return expr
        } else {
            throw IllegalArgumentException()
        }
    }

    private fun expr(): Expr {
        return term()
    }

    private fun term(): Expr {
        var expr = factor()

        while (match(TokenType.PLUS, TokenType.MINUS)) {
            val operator = previous()
            val right = factor()
            expr = Binary(expr, operator, right)
        }

        return expr
    }

    private fun factor(): Expr {
        var expr = primary()

        while (match(TokenType.STAR, TokenType.SLASH)) {
            val operator = previous()
            val right = primary()
            expr = Binary(expr, operator, right)
        }

        return expr
    }

    private fun primary(): Expr {
        if (match(TokenType.NUM)) {
            return Number(previous())
        }

        if (match(TokenType.LPAR)) {
            val expr = expr()
            if (check(TokenType.RPAR)) {
                advance()
                return Grouping(expr)
            } else {
                throw IllegalArgumentException()
            }
        }

        throw IllegalArgumentException()
    }

    private fun match(vararg types: TokenType): Boolean {
        for (type in types) {
            if (check(type)) {
                advance()
                return true
            }
        }
        return false
    }

    private fun check(type: TokenType): Boolean {
        if (isAtEnd()) {
            return false
        }
        return peek().type == type
    }

    private fun advance(): Token {
        if (!isAtEnd()) {
            current++
        }
        return previous()
    }

    private fun isAtEnd() = peek().type == TokenType.EOF

    private fun peek() = tokens[current]

    private fun previous() = tokens[current - 1]
}