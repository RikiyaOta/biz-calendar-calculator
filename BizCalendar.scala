import java.time.{LocalDate, DayOfWeek}
import DateHelper._
import CalendarType._

abstract class BizCalendar(date: LocalDate) {
  def +(n: Int): BizCalendar
  def -(n: Int): BizCalendar
  // キャスト演算子`_`はそのままは定義できなかったので`__`で実装した。
  def __(calendarType: CalendarType): BizCalendar = {
    calendarType match {
      case C => CommonCalendar(date)
      case Jp => {
        if(isJpOpened(date)) {
          JpBizCalendar(date)
        } else {
          CommonCalendar(date.plusDays(1)) __ Jp
        }
      }
      case Us => {
        if(isUsOpened(date)) {
          UsBizCalendar(date)
        } else {
          CommonCalendar(date.plusDays(1)) __ Us
        }
      }
      case JpAndUs => {
        if(isJpOpened(date) && isUsOpened(date)) {
          JpAndUsBizCalendar(date)
        } else {
          CommonCalendar(date.plusDays(1)) __ JpAndUs
        }
      }
      case JpOrUs => {
        if(isJpOpened(date) || isUsOpened(date)) {
          JpOrUsBizCalendar(date)
        } else {
          CommonCalendar(date.plusDays(1)) __ JpOrUs
        }
      }
    }
  }
  def ^(calendarType: CalendarType): BizCalendar = {
    calendarType match {
      case C => CommonCalendar(date)
      case Jp => {
        if(isJpOpened(date)) {
          JpBizCalendar(date)
        } else {
          CommonCalendar(date.minusDays(1)) ^ Jp
        }
      }
      case Us => {
        if(isUsOpened(date)) {
          UsBizCalendar(date)
        } else {
          CommonCalendar(date.minusDays(1)) ^ Us
        }
      }
      case JpAndUs => {
        if(isJpOpened(date) && isUsOpened(date)) {
          JpAndUsBizCalendar(date)
        } else {
          CommonCalendar(date.minusDays(1)) ^ JpAndUs
        }
      }
      case JpOrUs => {
        if(isJpOpened(date) || isUsOpened(date)) {
          JpOrUsBizCalendar(date)
        } else {
          CommonCalendar(date.minusDays(1)) ^ JpOrUs
        }
      }
    }
  }
}

case class CommonCalendar(date: LocalDate) extends BizCalendar(date) {
  def +(n: Int) = CommonCalendar(date.plusDays(n))
  def -(n: Int) = CommonCalendar(date.minusDays(n))
}
case class JpBizCalendar(date: LocalDate) extends BizCalendar(date) {
  def +(n: Int): JpBizCalendar = {
    if(n == 0) {
      this
    } else {
      val nextDate = date.plusDays(1)
      if(isJpOpened(nextDate)) {
        JpBizCalendar(nextDate) + (n-1)
      } else {
        JpBizCalendar(nextDate) + n
      }
    }
  }
  def -(n: Int): JpBizCalendar = {
    if(n == 0) {
      this
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
case class UsBizCalendar(date: LocalDate) extends BizCalendar(date) {
  def +(n: Int): UsBizCalendar = {
    if(n == 0) {
      this
    } else {
      val nextDate = date.plusDays(1)
      if(isUsOpened(nextDate)) {
        UsBizCalendar(nextDate) + (n-1)
      } else {
        UsBizCalendar(nextDate) + n
      }
    }
  }
  def -(n: Int): UsBizCalendar = {
    if(n == 0) {
      this
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
case class JpAndUsBizCalendar(date: LocalDate) extends BizCalendar(date) {
  def +(n: Int): JpAndUsBizCalendar = {
    if(n == 0) {
      this
    } else {
      val nextDate = date.plusDays(1)
      if (isJpOpened(nextDate) && isUsOpened(nextDate)) {
        JpAndUsBizCalendar(nextDate) + (n-1)
      } else {
        JpAndUsBizCalendar(nextDate) + n
      }
    }
  }
  def -(n: Int): JpAndUsBizCalendar = {
    if(n == 0) {
      this
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
case class JpOrUsBizCalendar(date: LocalDate) extends BizCalendar(date) {
  def +(n: Int): JpOrUsBizCalendar = {
    if(n == 0) {
      this
    } else {
      val nextDate = date.plusDays(1)
      if(isJpOpened(nextDate) || isUsOpened(nextDate)) {
        JpOrUsBizCalendar(nextDate) + (n-1)
      } else {
        JpOrUsBizCalendar(nextDate) + n
      }
    }
  }
  def -(n: Int): JpOrUsBizCalendar = {
    if(n == 0) {
      this
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


