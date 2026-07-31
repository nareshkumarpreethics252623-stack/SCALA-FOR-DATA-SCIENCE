import com.github.tototoshi.csv._

object OneHotEncoding {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open("data/colordataset.csv")

    val rows = reader.all()

    reader.close()

    val header = rows.head
    val data = rows.tail

    val columnName = "Color"
    val columnIndex = header.indexOf(columnName)

    // Get unique colors
    val categories = data.map(_(columnIndex)).distinct

    // Print new header
    val newHeader = header.patch(columnIndex, categories.map("Color_" + _), 1)
    println(newHeader.mkString(","))

    // Encode each row
    for (row <- data) {

      val color = row(columnIndex)

      val encoded = categories.map { category =>
        if (category == color) "1" else "0"
      }

      val newRow = row.patch(columnIndex, encoded, 1)

      println(newRow.mkString(","))
    }
  }
}
