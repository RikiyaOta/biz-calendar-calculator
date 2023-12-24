import java.time.LocalDate
import CalendarType._
import DateHelper._

object CastCalendarInstance {
  implicit val cc: CastCalendar = new CastCalendar {
    def forward(calendar: Calendar, calendarType: CalendarType): Calendar = {
      doCast(
        calendar,
        calendarType,
        (date: LocalDate) => date.plusDays(1)
      )
    }
    def back(calendar: Calendar, calendarType: CalendarType): Calendar = {
      doCast(
        calendar,
        calendarType,
        (date: LocalDate) => date.minusDays(1)
      )
    }
  }

  private def doCast(calendar: Calendar, calendarType: CalendarType, updator: LocalDate => LocalDate): Calendar = {
    val date = calendar match {
      case CommonCalendar(date) => date
      case JpBizCalendar(date) => date
      case UsBizCalendar(date) => date
      case JpAndUsBizCalendar(date) => date
      case JpOrUsBizCalendar(date) => date
    }

    calendarType match {
      case C => CommonCalendar(date)
      case Jp => {
        if(isJpOpened(date)) {
          JpBizCalendar(date)
        } else {
          doCast(CommonCalendar(updator(date)), calendarType, updator)
        }
      }
      case Us => {
        if(isUsOpened(date)) {
          UsBizCalendar(date)
        } else {
          doCast(CommonCalendar(updator(date)), calendarType, updator)
        }
      }
      case JpAndUs => {
        if(isJpOpened(date) && isUsOpened(date)) {
          JpAndUsBizCalendar(date)
        } else {
          doCast(CommonCalendar(updator(date)), calendarType, updator)
        }
      }
      case JpOrUs => {
        if(isJpOpened(date) || isUsOpened(date)) {
          JpOrUsBizCalendar(date)
        } else {
          doCast(CommonCalendar(updator(date)), calendarType, updator)
        }
      }
    }
  }
}
