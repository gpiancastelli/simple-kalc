package com.example.simplekalc.lexer

enum class TokenType {
    LPAR,
    RPAR,
    PLUS,
    MINUS,
    STAR,
    SLASH,
    NUM,
    EOF
}

data class Token(
    val type: TokenType,
    val lexeme: String
)