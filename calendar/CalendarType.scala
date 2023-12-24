/**
  * カレンダー種別
  */
object CalendarType {
  sealed trait CalendarType
  case object C extends CalendarType
  case object Jp extends CalendarType
  case object Us extends CalendarType
  case object JpAndUs extends CalendarType
  case object JpOrUs extends CalendarType
}
