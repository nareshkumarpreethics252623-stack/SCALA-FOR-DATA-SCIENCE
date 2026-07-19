import com.github.tototoshi.csv._

object CSVStatistics {

  def main(args: Array[String]): Unit = {

    // Change the path if your file is stored elsewhere
    val file = new java.io.File("NFL Play by Play 2009-2016.csv")

    val reader = CSVReader.open(file)

    val rows = reader.all()

    reader.close()

    // Header
    val header = rows.head

    // Data rows
    val data = rows.tail

    println("Basic Statistics for Numeric Columns")
    println("------------------------------------")

    for (i <- header.indices) {

      // Extract numeric values from the column
      val values = data.flatMap { row =>
        if (i < row.length) {
          try {
            Some(row(i).trim.toDouble)
          } catch {
            case _: Exception => None
          }
        } else None
      }

      if (values.nonEmpty) {

        val count = values.length
        val sum = values.sum
        val mean = sum / count
        val minimum = values.min
        val maximum = values.max

        println("\nColumn: " + header(i))
        println("Count   : " + count)
        println("Sum     : " + sum)
        println("Mean    : " + mean)
        println("Minimum : " + minimum)
        println("Maximum : " + maximum)
      }
    }
  }
}