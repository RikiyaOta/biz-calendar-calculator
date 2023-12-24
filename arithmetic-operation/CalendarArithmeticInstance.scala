import java.time.LocalDate
import CalendarType._
import DateHelper._
import BizCalendarSyntax.ArithmeticCalendarOps
import CommonCalendarArithmeticInstance._
import JpBizCalendarArithmeticInstance._
import UsBizCalendarArithmeticInstance._
import JpAndUsBizCalendarInstance._
import JpOrUsBizCalendarInstance._

object CalendarArithmeticInstance {
  implicit val calendarArithmetic: ArithmeticCalendar[Calendar] = new ArithmeticCalendar[Calendar] {
    def plus(calendar: Calendar, n: Int): Calendar = {
      calendar match {
        case c: CommonCalendar => c + n
        case c: JpBizCalendar => c + n
        case c: UsBizCalendar => c + n
        case c: JpAndUsBizCalendar => c + n
        case c: JpOrUsBizCalendar => c + n
      }
    }
    def minus(calendar: Calendar, n: Int): Calendar = {
      calendar match {
        case c: CommonCalendar => c - n
        case c: JpBizCalendar => c - n
        case c: UsBizCalendar => c - n
        case c: JpAndUsBizCalendar => c - n
        case c: JpOrUsBizCalendar => c - n
      }
    }
  }
}
