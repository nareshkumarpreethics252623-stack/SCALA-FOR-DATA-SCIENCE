//AIM :
//12. Join two CSV files in Spark DataFrames based on a common column and write the output to a file.

import org.apache.spark.sql.SparkSession

object JoinCSVFiles {

  def main(args: Array[String]): Unit = {

    // Create Spark Session
    val spark = SparkSession.builder()
      .appName("Join CSV Files")
      .master("local[*]")
      .getOrCreate()

    // Read first CSV file
    val df1 = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("students.csv")

    // Read second CSV file
    val df2 = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("marks.csv")

    // Display input DataFrames
    println("Students Data:")
    df1.show()

    println("Marks Data:")
    df2.show()

    // Join both DataFrames using the common column "Student_ID"
    val joinedDF = df1.join(
      df2,
      df1("Student_ID") === df2("Student_ID"),
      "inner"
    )

    // Display joined data
    println("Joined Data:")
    joinedDF.show()

    // Write the result to a CSV file
    joinedDF.write
      .mode("overwrite")
      .option("header", "true")
      .csv("joined_output")

    println("Joined data written successfully to joined_output")

    // Stop Spark
    spark.stop()
  }
}
