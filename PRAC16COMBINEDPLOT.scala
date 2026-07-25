import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object CafeCombinedPlot {
  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("cafe_revenue.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val days = DenseVector((1 to data.length).map(_.toDouble).toArray)
    val revenue = DenseVector(data.map(_("Revenue").toDouble).toArray)

    val window = 5
    val movingAvg = DenseVector((0 until revenue.length).map { i =>
      val start = math.max(0, i - window + 1)
      val slice = revenue(start to i)
      breeze.stats.mean(slice)
    }.toArray)

    val fig = Figure()
    val plt = fig.subplot(0)

    plt.title = "Cafe Revenue: Actual vs Trend (Moving Average)"
    plt.xlabel = "Day"
    plt.ylabel = "Revenue (Rs.)"

    plt += plot(days, revenue, '.', name = "Actual Revenue", colorcode = "[255,165,0]")
    plt += plot(days, movingAvg, '-', name = "Trend (5-day Avg)", colorcode = "k")

    fig.refresh()
    fig.saveas("combined_scatter_line.png")

    println("Combined plot generated: combined_scatter_line.png")
  }
}