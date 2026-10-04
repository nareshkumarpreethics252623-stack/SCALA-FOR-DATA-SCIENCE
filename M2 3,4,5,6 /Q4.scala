//AIM :
//4. Sort a dataset by a specific column and extract the top 5 rows.

import com.github.tototoshi.csv.CSVReader
import java.io.File

object DatasetSorting {

  def main(args: Array[String]): Unit = {

    val filePath = "Houseprices_data.csv"

    val reader = CSVReader.open(new File(filePath))

    val allRows = reader.allWithHeaders()

    reader.close()

    val sortColumn = "price"

    val topRows = allRows
      .sortBy(row => row(sortColumn).toDouble)(Ordering[Double].reverse)
      .take(5)

    println("Top 5 rows sorted by " + sortColumn + ":")

    topRows.foreach(row => println(row))
  }
}
