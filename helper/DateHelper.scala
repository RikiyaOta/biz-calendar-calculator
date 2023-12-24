import java.time.{LocalDate, DayOfWeek}
import scala.io.Source

object DateHelper {
  private def isWeekend(date: LocalDate) =
    date.getDayOfWeek() == DayOfWeek.SATURDAY | date.getDayOfWeek() == DayOfWeek.SUNDAY

  private def isJpHoliday(date: LocalDate) =
    JpHolidaySet.isHoliday(date)

  private def isUsHoliday(date: LocalDate) =
    UsHolidaySet.isHoliday(date)

  private def isJpClosed(date: LocalDate) =
    isWeekend(date) || isJpHoliday(date)

  private def isUsClosed(date: LocalDate) =
    isWeekend(date) || isUsHoliday(date)

  def isJpOpened(date: LocalDate) = !isJpClosed(date)

  def isUsOpened(date: LocalDate) = !isUsClosed(date)
}

object JpHolidaySet {
  private val value = {
    val filePath = "holidays/jp_holidays.txt"
    Source.fromFile(filePath)
      .getLines()
      .map(line => LocalDate.parse(line))
      .toSet
  }

  def isHoliday(date: LocalDate): Boolean =
    value.contains(date)
}

object UsHolidaySet {
  private val value = {
    val filePath = "holidays/us_holidays.txt"
    Source.fromFile(filePath)
      .getLines()
      .map(line => LocalDate.parse(line))
      .toSet
  }
  def isHoliday(date: LocalDate): Boolean =
    value.contains(date)
}

