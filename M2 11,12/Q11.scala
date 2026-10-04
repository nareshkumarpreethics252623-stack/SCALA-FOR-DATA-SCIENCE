//AIM :
//11. Perform a group-by operation in Spark DataFrames to compute the average of each group.

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object GroupByAverage {

  def main(args: Array[String]): Unit = {

    // Create Spark Session
    val spark = SparkSession.builder()
      .appName("Group By Average")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    // Create sample dataset
    val data = Seq(
      ("Computer Science", 80),
      ("Computer Science", 90),
      ("Computer Science", 75),
      ("Mathematics", 85),
      ("Mathematics", 95),
      ("Mathematics", 80),
      ("Physics", 70),
      ("Physics", 75),
      ("Physics", 85)
    )

    // Convert dataset to DataFrame
    val df = data.toDF("Department", "Marks")

    println("Original Data:")
    df.show()

    // Group by Department and calculate average marks
    val result = df
      .groupBy("Department")
      .agg(avg("Marks").alias("Average_Marks"))

    println("Average Marks by Department:")
    result.show()

    // Stop Spark
    spark.stop()
  }
}
