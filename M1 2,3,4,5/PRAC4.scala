import breeze.linalg._

object BreezeVectorExample {

  def main(args: Array[String]): Unit = {

    val vector1 = DenseVector(1.0, 2.0, 3.0, 4.0, 5.0)
    val vector2 = DenseVector(5.0, 4.0, 3.0, 2.0, 1.0)

    val sum = breeze.linalg.sum(vector1)
    val mean = breeze.stats.mean(vector1)
    val dotProduct = vector1 dot vector2

    println("Vector 1: " + vector1)
    println("Vector 2: " + vector2)
    println(s"Sum of Vector 1: $sum")
    println(s"Mean of Vector 1: $mean")
    println(s"Dot Product: $dotProduct")
  }
}
