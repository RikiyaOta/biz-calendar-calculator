import java.time.LocalDate
import CalendarType._
import DateHelper._
import BizCalendarSyntax.ArithmeticCalendarOps

object UsBizCalendarArithmeticInstance {
  implicit val usBzCalendarArithmetic: ArithmeticCalendar[UsBizCalendar] = new ArithmeticCalendar[UsBizCalendar] {
    def plus(calendar: UsBizCalendar, n: Int): UsBizCalendar = {
      val date = calendar.date
      if(n == 0) {
        UsBizCalendar(date)
      } else {
        val nextDate = date.plusDays(1)
        if(isUsOpened(nextDate)) {
          UsBizCalendar(nextDate) + (n-1)
        } else {
          UsBizCalendar(nextDate) + n
        }
      }
    }
    def minus(calendar: UsBizCalendar, n: Int): UsBizCalendar = {
      val date = calendar.date
      if(n == 0) {
        UsBizCalendar(date)
      } else {
        val previousDate = date.minusDays(1)
        if(isUsOpened(previousDate)) {
          UsBizCalendar(previousDate) - (n-1)
        } else {
          UsBizCalendar(previousDate) - n
        }
      }
    }
  }
}
