import CalendarType._

object BizCalendarSyntax {
  implicit class CastCalendarOps(calendar: Calendar) {
    def __(calendarType: CalendarType)(implicit cc: CastCalendar) =
      cc.forward(calendar, calendarType)

    def ^(calendarType: CalendarType)(implicit cc: CastCalendar): Calendar =
      cc.back(calendar, calendarType)
  }

  implicit class ArithmeticCalendarOps[A](calendar: A) {
    def +(n: Int)(implicit ac: ArithmeticCalendar[A]) =
      ac.plus(calendar, n)

    def -(n: Int)(implicit ac: ArithmeticCalendar[A]) =
      ac.minus(calendar, n)
  }
}
