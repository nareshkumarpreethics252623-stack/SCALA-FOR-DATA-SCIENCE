import com.github.tototoshi.csv._

object CSVSTATISTICS {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open("DATA/NFL Play by Play 2009-2016 (v3).csv")

    val iterator = reader.iterator

    val headers = iterator.next()

    val sums = Array.fill(headers.length)(0.0)
    val counts = Array.fill(headers.length)(0)
    val mins = Array.fill(headers.length)(Double.MaxValue)
    val maxs = Array.fill(headers.length)(Double.MinValue)

    for (row <- iterator) {

      for (i <- row.indices) {

        try {
          val value = row(i).trim.toDouble

          sums(i) += value
          counts(i) += 1

          if (value < mins(i)) mins(i) = value
          if (value > maxs(i)) maxs(i) = value

        } catch {
          case _: Exception =>
        }
      }
    }

    reader.close()

    println("===== BASIC STATISTICS =====")

    for (i <- headers.indices) {

      if (counts(i) > 0) {

        println("\nColumn: " + headers(i))
        println("Count   : " + counts(i))
        println("Mean    : " + (sums(i) / counts(i)))
        println("Minimum : " + mins(i))
        println("Maximum : " + maxs(i))
      }
    }
  }
}
