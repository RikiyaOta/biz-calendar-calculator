trait ArithmeticCalendar[A] {
  def plus(calendar: A, n: Int): A
  def minus(calendar: A, n: Int): A
}

