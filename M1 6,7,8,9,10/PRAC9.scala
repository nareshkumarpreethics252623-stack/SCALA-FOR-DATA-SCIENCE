import com.github.tototoshi.csv._

object MissingValueHandler {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open("DATA/studentdataset.csv")

    val rows = reader.all()

    reader.close()

    val header = rows.head
    val data = rows.tail

    val numericCols = 1 until header.length

    val means = numericCols.map { col =>

      val values = data.flatMap { row =>
        try {
          if (row(col).trim.isEmpty) None
          else Some(row(col).toDouble)
        } catch {
          case _: Exception => None
        }
      }

      // Mean
      val mean = values.sum / values.length

      // Median
      val sortedValues = values.sorted
      val median =
        if (sortedValues.length % 2 == 0)
          (sortedValues(sortedValues.length / 2 - 1) +
            sortedValues(sortedValues.length / 2)) / 2
        else
          sortedValues(sortedValues.length / 2)

      // Mode
      val mode = values.groupBy(identity)
        .mapValues(_.size)
        .maxBy(_._2)._1

      println("\nColumn: " + header(col))
      println("Mean   : " + mean)
      println("Median : " + median)
      println("Mode   : " + mode)

      mean
    }

    println("\nDataset After Replacing Missing Values:\n")

    println(header.mkString(","))

    data.foreach { row =>

      val updatedRow = row.toArray

      for (i <- numericCols) {

        if (updatedRow(i).trim.isEmpty) {
          updatedRow(i) = means(i - 1).formatted("%.2f")
        }
      }

      println(updatedRow.mkString(","))
    }
  }
}
