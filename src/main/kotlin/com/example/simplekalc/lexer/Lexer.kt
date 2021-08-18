package com.example.simplekalc.lexer

private const val NULL_CHAR = '\u0000'

class Lexer {
    private var source = ""
    private var start = 0
    private var current = 0

    fun readTokens(source: String): List<Token> {
        reset()
        this.source = source

        val tokens = mutableListOf<Token>()

        while (!isAtEnd()) {
            start = current
            tokens.add(scanToken())
        }
        tokens.add(Token(TokenType.EOF, ""))
        return tokens
    }

    private fun reset() {
        source = ""
        start = 0
        current = 0
    }

    private fun isAtEnd() = current >= source.length

    private fun scanToken(): Token {
        var c = advance()
        while (c.isWhitespace()) {
            start++
            c = advance()
        }
        return when (c) {
            '(' -> createToken(TokenType.LPAR)
            ')' -> createToken(TokenType.RPAR)
            '+' -> createToken(TokenType.PLUS)
            '-' -> createToken(TokenType.MINUS)
            '*' -> createToken(TokenType.STAR)
            '/' -> createToken(TokenType.SLASH)
            else -> {
                if (c.isDigit()) {
                    createNumber()
                } else {
                    throw IllegalArgumentException()
                }
            }
        }
    }

    private fun createNumber(): Token {
        while (peek().isDigit()) {
            advance()
        }
        if (peek() == '.' && peekNext().isDigit()) {
            advance()
            while (peek().isDigit()) {
                advance()
            }
        }

        return createToken(TokenType.NUM)
    }

    private fun createToken(type: TokenType) = Token(type, source.substring(start, current))

    private fun advance() = source[current++]

    private fun peek() = if (isAtEnd()) NULL_CHAR else source[current]

    private fun peekNext() = if (current + 1 >= source.length) NULL_CHAR else source[current + 1]
}