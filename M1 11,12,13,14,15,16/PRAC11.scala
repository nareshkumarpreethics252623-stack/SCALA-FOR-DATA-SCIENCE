import scala.io.Source

object WordFrequencyCounter {

  def main(args: Array[String]): Unit = {

    val filePath = "data/sample.txt"

    // Read file content
    val text = Source.fromFile(filePath).mkString

    // Tokenize words and convert to lowercase
    val words = text
      .toLowerCase()
      .split("\\W+")
      .filter(_.nonEmpty)

    // Count word frequencies
    val wordCount = words.groupBy(identity).view.mapValues(_.length).toMap

    // Display frequencies
    println("Word Frequencies:")
    wordCount.toSeq.sortBy(_._1).foreach {
      case (word, count) =>
        println(s"$word -> $count")
    }
  }
}
