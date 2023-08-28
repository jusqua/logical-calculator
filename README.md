# logical-calculator
It is a logical calculator made in Java to evaluate logical expressions

#### See a preview on [Replit](https://replit.com/@jusqua/logical-calculator)

### Build
```bash
javac Main.java && java Main
```

### Usage
  - The __operators__ and __operands__ are comma (,) separated
  - The __binary__ operators operate on the last two operands
  - The __unary__ operators operate only on the last operand
  - The __operators__ are reserved, therefore they are not used as operands
  - The __false__ and __true__ are reserved and operate like yours truth values

  |Operators  |Description   |
  |:---------:|:------------:|
  |~          |Negates       |
  |^          |AND           |
  |v          |AND/OR        |
  |=>         |Conditional   |
  |<=>        |Biconditional |

  |Operand    |Description       |
  |:---------:|:----------------:|
  |false      |Always False      |
  |true       |Always True       |

### Exemples
  |Input                |Equivalent Expression  |Output        |
  |---------------------|-----------------------|--------------|
  |p,q,^                |p ^ q                  |undefined     |
  |false,~,p,v,true,^   |(~false v p) ^ true    |tautology     |
  |p1,q1,v,~,p1,q1,v,^  |~(p1 v q1) ^ (p1 v q1) |contradiction |
  |s,t,~,v,s,t,=>,<=>   |(s v ~t) <=> (s => t)  |undefined     |
  |a,~,b,v,a,b,=>,<=>   |(~a v b) <=> (a => b)  |tautology     |

### Members
  - [Ádrian Gama](https://github.com/jusqua)
  - [Carlos Adriano](https://github.com/biribas)
