trait AddSubtCalendar[A] {
  def +(n: Int): AddSubtCalendar[A]
  def -(n: Int): AddSubtCalendar[A]
}
