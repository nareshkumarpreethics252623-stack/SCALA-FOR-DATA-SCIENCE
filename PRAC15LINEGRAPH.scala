import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object CafeLineGraph {
  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("cafe_revenue.csv"))
    val data = reader.allWithHeaders()
    reader.close()
    
    val days = DenseVector((1 to data.length).map(_.toDouble).toArray)
    val revenue = DenseVector(data.map(_("Revenue").toDouble).toArray)

    val fig = Figure()
    val plt = fig.subplot(0)

    plt.title = "Cafe Revenue Trend Over Time"
    plt.xlabel = "Day"
    plt.ylabel = "Revenue (Rs.)"

    plt += plot(days, revenue, '-', colorcode = "blue")

    fig.refresh()
    fig.saveas("line_revenue_trend.png")

    println("Line graph generated: line_revenue_trend.png")
  }
}