import java.time.LocalDate
import CalendarType._
import DateHelper._
import BizCalendarSyntax.ArithmeticCalendarOps

object JpOrUsBizCalendarInstance {
  implicit val jpOrUsBzCalendarArithmetic: ArithmeticCalendar[JpOrUsBizCalendar] = new ArithmeticCalendar[JpOrUsBizCalendar] {
    def plus(calendar: JpOrUsBizCalendar, n: Int): JpOrUsBizCalendar = {
      val date = calendar.date
      if(n == 0) {
        JpOrUsBizCalendar(date)
      } else {
        val nextDate = date.plusDays(1)
        if(isJpOpened(nextDate) || isUsOpened(nextDate)) {
          JpOrUsBizCalendar(nextDate) + (n-1)
        } else {
          JpOrUsBizCalendar(nextDate) + n
        }
      }
    }
    def minus(calendar: JpOrUsBizCalendar, n: Int): JpOrUsBizCalendar = {
      val date = calendar.date
      if(n == 0) {
        JpOrUsBizCalendar(date)
      } else {
        val previousDate = date.minusDays(1)
        if(isJpOpened(previousDate) || isUsOpened(previousDate)) {
          JpOrUsBizCalendar(previousDate) - (n-1)
        } else {
          JpOrUsBizCalendar(previousDate) - n
        }
      }
    }
  }
}
