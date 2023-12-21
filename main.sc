import java.time.LocalDate

args.toList match {
  case Nil =>
    println(
      """|引数を指定してください。
         | $1: Expression (required)
         | $2: Target Date (option, default: Today)""".stripMargin)
  case h :: Nil => println(new BizCalendarDslCalculator(LocalDate.now()).parse(h))
  case h :: t => println(new BizCalendarDslCalculator(LocalDate.parse(t.head)).parse(h))
}
