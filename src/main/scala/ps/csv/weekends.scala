package ps.csv

import scala.util.Try

/**
  * Created by valik on 21.11.16.
  * parse opendata from http://data.gov.ru/opendata/7708660670-proizvcalendar
  * Производственный календарь
  *
  * Output csv of all holidays and weekends mentioned in file downloaded from opendata.
  *
  * execute program:
  * java -cp ~/code/weekends/target/scala-2.12/weekends-assembly-1.0.jar \
  *     ps.csv.weekends \
  *     /media/sf_t/data-20161107T1038-structure-20161107T1038.csv > weekends.lst
  */
object weekends {
    def main(args: Array[String]): Unit = {
        if (args.length <= 0) sys.error("You have to pass a csv file as a parameter")
        System.err.println(s"weekends file: ${args(0)}")

        convertDates(args(0))

        def convertDates(fname: String): Unit = {
            import com.github.tototoshi.csv._
            val inp = CSVReader.open(fname)

            inp.foreach(fields => doOneYear(fields))
            inp.close()
        }

        def doOneYear(fields: Seq[String]): Unit = {
            val year = Try { fields(0).toInt }
                .map { y => doYear(y, fields) }
                .foreach( outpYear(_) )
        }

        def outpYear(dates: Seq[String]): Unit = {
            for (date <- dates) println(date)
        }

        def doYear(year: Int, fields: Seq[String]): Seq[String] = {
            for {
                month <- 1 to 12
                lst = fields(month)
                day <- lst.split(",")
                date <- makeDate(year, month, day)
            } yield date
        }

        def makeDate(year: Int, month: Int, day: String): Option[String] = {
            Try { day.toInt }.map( d => f"$year-$month%02d-$d%02d" ).toOption
        }
    }
}
