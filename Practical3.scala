import scala.util.Random
import scala.math.sqrt

object Practical3 {
  def main(args: Array[String]): Unit = {

    // Generate 10 random integers between 1 and 100
    val data = Array.fill(10)(Random.nextInt(100) + 1)

    println("Dataset:")
    println(data.mkString(", "))

    // Calculate mean
    val mean = data.sum.toDouble / data.length

    // Calculate variance (population variance)
    val variance = data.map(x => math.pow(x - mean, 2)).sum / data.length

    // Calculate standard deviation
    val standardDeviation = sqrt(variance)

    println(f"\nMean: $mean%.2f")
    println(f"Variance: $variance%.2f")
    println(f"Standard Deviation: $standardDeviation%.2f")
  }
}