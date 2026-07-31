import scala.io.Source
import breeze.plot._
import breeze.linalg._

object MovingAverages {
  def main(args: Array[String]): Unit = {

    val filePath = "src/main/resources/website_visitors_timeseries.csv"
    val file = Source.fromFile(filePath)

    val rows = file.getLines().drop(1).flatMap { line =>
      val cols = line.split(",")
      for {
        day   <- cols(1).trim.toIntOption
        value <- cols(2).trim.toDoubleOption
      } yield (day, value)
    }.toList
    file.close()

    val dayIndex = rows.map(_._1)
    val visitors = rows.map(_._2)
    val n = visitors.length
    val windowSize = 7

    def windows(data: List[Double], w: Int): List[Option[List[Double]]] =
      (0 until data.length).map(i => if (i + 1 < w) None else Some(data.slice(i - w + 1, i + 1))).toList

    // Simple Moving Average
    val sma = windows(visitors, windowSize).map(_.map(w => w.sum / w.length))

    // Weighted Moving Average
    val weights = (1 to windowSize).map(_.toDouble)
    val weightSum = weights.sum
    val wma = windows(visitors, windowSize).map(_.map(w => w.zip(weights).map(t => t._1 * t._2).sum / weightSum))

    // Exponential Moving Average
    val alpha = 2.0 / (windowSize + 1)
    val ema = visitors.scanLeft(visitors.head)((prev, curr) => alpha * curr + (1 - alpha) * prev).tail

    // Visualization
    val f = Figure()
    val p = f.subplot(0)
    val xAxis = DenseVector(dayIndex.map(_.toDouble).toArray)
    p += plot(xAxis, DenseVector(visitors.toArray), name = "Actual")
    p += plot(xAxis, DenseVector(sma.map(_.getOrElse(Double.NaN)).toArray), name = "SMA")
    p += plot(xAxis, DenseVector(wma.map(_.getOrElse(Double.NaN)).toArray), name = "WMA")
    p += plot(xAxis, DenseVector(ema.toArray), name = "EMA")
    p.xlabel = "Day Index"
    p.ylabel = "Website Visitors"
    p.title = "Actual vs SMA vs WMA vs EMA"
    p.legend = true
    f.saveas("moving_averages.png")

    println(f"Dataset Size: $n%d records")
    println(f"Window Size: $windowSize%d | EMA alpha: $alpha%.4f")
    println("Plot saved as moving_averages.png")

    println(
      """
        |Inference: SMA smooths noise but lags trend changes. WMA reacts faster since recent
        |days get more weight. EMA reacts fastest since it continuously weighs all past data.
        |Best fit: EMA/WMA suit this trending, seasonal dataset better than SMA, as they track
        |the upward trend with less lag.
        |""".stripMargin)
  }
}
