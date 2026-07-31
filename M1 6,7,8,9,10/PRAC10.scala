import com.github.tototoshi.csv._

object FilterRows {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open("data/weather_data.csv")

    val rows = reader.all()

    reader.close()

    val header = rows.head
    val data = rows.tail

    val columnName = "Temperature_C"
    val threshold = 30.0

    val columnIndex = header.indexOf(columnName)

    println(s"Rows where $columnName > $threshold\n")

    println(header.mkString(","))

    data.foreach { row =>

      try {

        val value = row(columnIndex).toDouble

        if (value > threshold) {
          println(row.mkString(","))
        }

      } catch {
        case _: Exception =>
      }
    }
  }
}
