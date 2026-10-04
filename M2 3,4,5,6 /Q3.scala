// AIM 
// Write a program to compute frequency distribution and cumulative frequency of a dataset.


import scala.io.Source

object FrequencyDistribution {

  def main(args: Array[String]): Unit = {

    val path = "screen_time_mental_health.csv"
    val lines = Source.fromFile(path).getLines().toList
    val header = lines.head.split(",").map(_.trim)
    val bdiIndex = header.indexOf("bdi_total")

    val scores = lines.tail
      .map(_.split(","))
      .filter(_.length > bdiIndex)
      .map(_(bdiIndex).trim.toDouble.toInt)

    val classWidth = 5
    val minVal = scores.min
    val maxVal = scores.max
    val lowerStart = (minVal / classWidth) * classWidth

    val classIntervals = Iterator
      .iterate(lowerStart)(_ + classWidth)
      .takeWhile(_ <= maxVal)
      .map(low => (low, low + classWidth - 1))
      .toList

    val frequencies = classIntervals.map { case (low, high) =>
      val freq = scores.count(s => s >= low && s <= high)
      (low, high, freq)
    }

    var cumulative = 0
    val withCumulative = frequencies.map { case (low, high, freq) =>
      cumulative += freq
      (low, high, freq, cumulative)
    }

    println(s"Total records: ${scores.length}")
    println(s"BDI Score range: $minVal to $maxVal\n")
    println(f"${"Class Interval"}%-18s${"Frequency"}%-12s${"Cumulative Freq"}%-16s")
    println("-" * 46)

    withCumulative.foreach { case (low, high, freq, cumFreq) =>
      println(f"$low%-4d - $high%-10d$freq%-12d$cumFreq%-16d")
    }
  }
}
