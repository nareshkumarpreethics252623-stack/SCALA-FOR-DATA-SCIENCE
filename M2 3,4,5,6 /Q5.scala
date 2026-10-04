//AIM:
//5. Implement linear regression using Breeze. Fit a model to a small dataset and predict a value.

import breeze.linalg._
import breeze.stats._

object LinearRegression {

  def main(args: Array[String]): Unit = {

    // Sample dataset
    val x = DenseVector(1.0, 2.0, 3.0, 4.0, 5.0)
    val y = DenseVector(2.0, 4.0, 5.0, 4.0, 5.0)

    // Calculate means
    val xMean = mean(x)
    val yMean = mean(y)

    // Calculate slope (b1)
    val slope =
      sum((x - xMean) :* (y - yMean)) /
      sum((x - xMean) :^ 2.0)

    // Calculate intercept (b0)
    val intercept = yMean - slope * xMean

    // Display the model
    println("Linear Regression Model:")
    println(f"y = $intercept%.2f + $slope%.2f x")

    // Predict value for x = 6
    val xNew = 6.0
    val prediction = intercept + slope * xNew

    println(f"\nPrediction for x = $xNew%.1f:")
    println(f"Predicted y = $prediction%.2f")
  }
}
