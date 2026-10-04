//AIM :
//7. Compute the Euclidean distance between two Breeze vectors. Use it for nearest neighbor classification.

import breeze.linalg._

object NearestNeighbor {

  def main(args: Array[String]): Unit = {

    // Test data
    val testPoint = DenseVector(3.0, 4.0)

    // Training data
    val point1 = DenseVector(1.0, 2.0)
    val point2 = DenseVector(5.0, 6.0)
    val point3 = DenseVector(3.0, 3.0)

    // Labels of training points
    val label1 = "Class A"
    val label2 = "Class B"
    val label3 = "Class A"

    // Euclidean distance function
    def euclideanDistance(
        a: DenseVector[Double],
        b: DenseVector[Double]
    ): Double = {
      norm(a - b)
    }

    // Calculate distances
    val distance1 = euclideanDistance(testPoint, point1)
    val distance2 = euclideanDistance(testPoint, point2)
    val distance3 = euclideanDistance(testPoint, point3)

    println("Euclidean Distances:")
    println(f"Distance to Point 1: $distance1%.2f")
    println(f"Distance to Point 2: $distance2%.2f")
    println(f"Distance to Point 3: $distance3%.2f")

    // Find nearest neighbor
    val distances = Seq(
      (distance1, label1),
      (distance2, label2),
      (distance3, label3)
    )

    val nearest = distances.minBy(_._1)

    println("\nNearest Neighbor:")
    println(f"Distance: ${nearest._1}%.2f")
    println(s"Predicted Class: ${nearest._2}")
  }
}
