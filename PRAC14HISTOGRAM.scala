import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object CafeHistogram {
  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("cafe_revenue.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val orders = DenseVector(data.map(_("Orders").toDouble).toArray)

    val fig = Figure()
    val plt = fig.subplot(0)

    plt.title = "Distribution of Daily Orders"
    plt.xlabel = "Number of Orders"
    plt.ylabel = "Frequency"

    plt += hist(orders, bins = 15)

    fig.refresh()
    fig.saveas("histogram_orders_bins15.png")

    println("Histogram generated: histogram_orders_bins15.png")
  }
}