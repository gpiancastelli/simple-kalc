A calculator for simple arithmetic expressions, written in Kotlin. It only supports the basic four operations,
parentheses, and decimal numbers; in particular, no unary operators are available.

The project is heavily inspired by the book [Crafting Interpreters](https://craftinginterpreters.com/) and its
techniques on [scanning](https://craftinginterpreters.com/scanning.html),
[parsing](https://craftinginterpreters.com/parsing-expressions.html), and
[processing](https://craftinginterpreters.com/representing-code.html) expressions by the use of the Visitor pattern.

The main evaluator is based on `BigDecimal`, in order to avoid pitfalls in floating point operations, such as:

```
> 0.1 + 0.2
0.30000000000000004
```

Proper error management (for invalid syntax and stuff like division by zero) is currently missing. Any mistake will
throw an `IllegalArgumentException` and crash the program.