//> using dep org.scala-lang.modules::scala-parser-combinators:2.3.0

import scala.util.parsing.combinator._
import java.time.LocalDate
import CalendarType._
import CastCalendarInstance._
import BizCalendarSyntax._
import CalendarArithmeticInstance._
import CommonCalendarArithmeticInstance._
import JpBizCalendarArithmeticInstance._
import UsBizCalendarArithmeticInstance._
import JpAndUsBizCalendarInstance._
import JpOrUsBizCalendarInstance._

class BizCalendarDslCalculator(targetDate: LocalDate) extends JavaTokenParsers {
  private def binOp = "+" | "-"
  private def castOp = "_" | "^"
  private def cal = ("jp" | "us" | "c" | "jp&us" | "jp|us") ^^ {t =>
    t match {
      case "c" => C
      case "jp" => Jp
      case "us" => Us
      case "jp&us" => JpAndUs
      case "jp|us" => JpOrUs
    }
  }
  private def num = "(0|[1-9][0-9]*)".r ^^ { s => s.toInt }

  private def initTerm: Parser[Calendar] = "T" ^^ { t => CommonCalendar(targetDate) }
  private def term: Parser[Calendar] = initTerm | "(" ~> expression <~ ")"
  private def castTerm = term ~ castOp ~ cal ^^ { t =>
    t match {
      case calendar ~ "_" ~ calendarType => calendar __ calendarType
      case calendar ~ "^" ~ calendarType => calendar ^  calendarType
    }
  }
  private def expression: Parser[Calendar] = castTerm ~ opt(binOp ~ num) ^^ { t =>
    t match {
      case calendar ~ None => calendar
      case calendar ~ Some(binOpNum) => {
        binOpNum match {
          case "+" ~ n => calendar + n
          case "-" ~ n => calendar - n
        }
      }
    }
  }

  def parse(input: String) = parseAll(expression, input)
}
