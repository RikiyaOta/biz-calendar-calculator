//> using dep org.scala-lang.modules::scala-parser-combinators:2.3.0

import scala.util.parsing.combinator._
import java.time.LocalDate
import CalendarType._
import CastCalendarInstance._
import BizCalendarSyntax._

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
      case bizCalendar ~ "_" ~ calendar => bizCalendar __ calendar
      case bizCalendar ~ "^" ~ calendar => bizCalendar ^  calendar
    }
  }
  private def expression: Parser[Calendar] = castTerm ~ opt(binOp ~ num) ^^ { t =>
    t match {
      case bizCalendar ~ None => bizCalendar
      //case bizCalendar ~ Some(binOpNum) => {
      //  binOpNum match {
      //    case "+" ~ n => bizCalendar + n
      //    case "-" ~ n => bizCalendar - n
      //  }
      //}
    }
  }

  def parse(input: String) = parseAll(expression, input)
}
