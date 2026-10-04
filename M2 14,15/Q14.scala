//AIM : 
//14. Perform basic time series analysis in Scala. Generate synthetic time series data (e.g., daily sales over a month).

import scala.util.Random

object TimeSeriesAnalysis {

  def main(args: Array[String]): Unit = {

    val random = new Random()

    // Generate daily sales data for 30 days
    val salesData = (1 to 30).map { day =>
      val sales = 1000 + random.nextInt(501)
      (day, sales)
    }

    println("Daily Sales Data:")

    salesData.foreach { case (day, sales) =>
      println(f"Day $day%2d : ₹$sales%d")
    }

    // Calculate total sales
    val totalSales = salesData.map(_._2).sum

    // Calculate average daily sales
    val averageSales = totalSales.toDouble / salesData.length

    // Find maximum and minimum sales
    val maxSales = salesData.maxBy(_._2)
    val minSales = salesData.minBy(_._2)

    println("\nTime Series Analysis:")
    println(s"Total Sales       : ₹$totalSales")
    println(f"Average Sales     : ₹$averageSales%.2f")
    println(s"Highest Sales     : Day ${maxSales._1} = ₹${maxSales._2}")
    println(s"Lowest Sales      : Day ${minSales._1} = ₹${minSales._2}")
  }
}
