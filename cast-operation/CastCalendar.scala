import CalendarType._

trait CastCalendar {
  // _ 演算子
  def forward(calendar: Calendar, calendarType: CalendarType): Calendar

  // ^ 演算子
  def back(calendar: Calendar, calendarType: CalendarType): Calendar
}
