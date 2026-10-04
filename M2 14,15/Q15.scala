//AIM : 
//15. Create polynomial features from a dataset. Given a list of numbers (e.g., [1, 2, 3]), 
//generate polynomial features up to degree 3 (e.g., [1, 1^2, 1^3, 2, 2^2, 2^3, 3, 3^2, 3^3]).

object PolynomialFeatures {

  def main(args: Array[String]): Unit = {

    val data = List(1, 2, 3)
    val degree = 3

    println("Original Data:")
    println(data)

    println("\nPolynomial Features:")

    data.foreach { x =>
      val features = (1 to degree).map(power => math.pow(x, power).toInt)
      println(s"$x -> ${features.mkString(", ")}")
    }

    val allFeatures = data.flatMap { x =>
      (1 to degree).map(power => math.pow(x, power).toInt)
    }

    println("\nAll Polynomial Features:")
    println(allFeatures)
  }
}
