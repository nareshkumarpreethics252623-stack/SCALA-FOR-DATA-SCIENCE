//AIM : 
//6. Perform logistic regression using Breeze. Classify a dataset with binary labels.

import breeze.linalg._
import breeze.numerics._

object LogisticRegression {

  def main(args: Array[String]): Unit = {

    // Input dataset
    val x = DenseVector(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)

    // Binary labels: 0 or 1
    val y = DenseVector(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)

    // Initialize parameters
    var weight = 0.0
    var bias = 0.0

    val learningRate = 0.1
    val iterations = 1000

    // Sigmoid function
    def sigmoid(z: Double): Double = {
      1.0 / (1.0 + math.exp(-z))
    }

    // Gradient Descent
    for (_ <- 1 to iterations) {

      val predictions = x.map(value =>
        sigmoid(weight * value + bias)
      )

      val error = predictions - y

      val dw = sum(error :* x) / x.length
      val db = sum(error) / x.length

      weight -= learningRate * dw
      bias -= learningRate * db
    }

    // Display model parameters
    println("Logistic Regression Model:")
    println(f"Weight: $weight%.4f")
    println(f"Bias: $bias%.4f")

    // Classify the dataset
    println("\nClassification Results:")

    x.foreach { value =>
      val probability = sigmoid(weight * value + bias)
      val predictedClass = if (probability >= 0.5) 1 else 0

      println(
        f"x = $value%.1f, Probability = $probability%.4f, Predicted Class = $predictedClass"
      )
    }
  }
}
