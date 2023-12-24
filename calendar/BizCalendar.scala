import java.time.LocalDate

sealed trait Calendar
case class CommonCalendar(date: LocalDate) extends Calendar
case class JpBizCalendar(date: LocalDate) extends Calendar
case class UsBizCalendar(date: LocalDate) extends Calendar
case class JpAndUsBizCalendar(date: LocalDate) extends Calendar
case class JpOrUsBizCalendar(date: LocalDate) extends Calendar
