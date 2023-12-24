import java.time.LocalDate
import CalendarType._
import DateHelper._
import BizCalendarSyntax.ArithmeticCalendarOps

object JpBizCalendarArithmeticInstance {
  implicit val jpBizCalendarArithmetic: ArithmeticCalendar[JpBizCalendar] = new ArithmeticCalendar[JpBizCalendar] {
    def plus(calendar: JpBizCalendar, n: Int): JpBizCalendar = {
      val date = calendar.date
      if(n == 0) {
        JpBizCalendar(date)
      } else {
        val nextDate = date.plusDays(1)
        if(isJpOpened(nextDate)) {
          JpBizCalendar(nextDate) + (n-1)
        } else {
          JpBizCalendar(nextDate) + n
        }
      }
    }
    def minus(calendar: JpBizCalendar, n: Int): JpBizCalendar = {
      val date = calendar.date
      if(n == 0) {
        JpBizCalendar(date)
      } else {
        val previousDate = date.minusDays(1)
        if(isJpOpened(previousDate)) {
          JpBizCalendar(previousDate) - (n-1)
        } else {
          JpBizCalendar(previousDate) - n
        }
      }
    }
  }
}
