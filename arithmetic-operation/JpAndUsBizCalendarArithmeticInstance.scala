import java.time.LocalDate
import CalendarType._
import DateHelper._
import BizCalendarSyntax.ArithmeticCalendarOps

object JpAndUsBizCalendarInstance {
  implicit val jpAndUsBzCalendarArithmetic: ArithmeticCalendar[JpAndUsBizCalendar] = new ArithmeticCalendar[JpAndUsBizCalendar] {
    def plus(calendar: JpAndUsBizCalendar, n: Int): JpAndUsBizCalendar = {
      val date = calendar.date
      if(n == 0) {
        JpAndUsBizCalendar(date)
      } else {
        val nextDate = date.plusDays(1)
        if (isJpOpened(nextDate) && isUsOpened(nextDate)) {
          JpAndUsBizCalendar(nextDate) + (n-1)
        } else {
          JpAndUsBizCalendar(nextDate) + n
        }
      }
    }
    def minus(calendar: JpAndUsBizCalendar, n: Int): JpAndUsBizCalendar = {
      val date = calendar.date
      if(n == 0) {
        JpAndUsBizCalendar(date)
      } else {
        val previousDate = date.minusDays(1)
        if (isJpOpened(previousDate) && isUsOpened(previousDate)) {
          JpAndUsBizCalendar(previousDate) - (n-1)
        } else {
          JpAndUsBizCalendar(previousDate) - n
        }
      }
    }
  }
}
