import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object CafeScatterPlot {
  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("cafe_revenue.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val orders = DenseVector(data.map(_("Orders").toDouble).toArray)
    val revenue = DenseVector(data.map(_("Revenue").toDouble).toArray)

    val fig = Figure()
    val plt = fig.subplot(0)

    plt.title = "Cafe Revenue vs Orders"
    plt.xlabel = "Number of Orders"
    plt.ylabel = "Revenue (Rs.)"

    plt += plot(orders, revenue, '.', name = "Daily Sales", colorcode = "magenta")

    fig.refresh()
    fig.saveas("scatter_orders_vs_revenue.png")

    println("Scatter plot generated successfully: scatter_orders_vs_revenue.png")
  }
}
