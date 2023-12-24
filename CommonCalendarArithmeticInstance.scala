import java.time.LocalDate
import CalendarType._
import DateHelper._

object CommonCalendarArithmeticInstance {
  implicit val commonCalendarArithmetic: ArithmeticCalendar[CommonCalendar] = new ArithmeticCalendar[CommonCalendar] {
    def plus(calendar: CommonCalendar, n: Int): CommonCalendar = CommonCalendar(calendar.date.plusDays(n))
    def minus(calendar: CommonCalendar, n: Int): CommonCalendar = CommonCalendar(calendar.date.minusDays(n))
  }
}
