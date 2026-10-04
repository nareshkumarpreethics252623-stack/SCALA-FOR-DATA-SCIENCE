//AIM: 
//8. Cluster a dataset into two groups using k-means clustering in Breeze.

import breeze.linalg._
import breeze.numerics._

object KMeansClustering {

  def main(args: Array[String]): Unit = {

    // Sample dataset
    val data = DenseMatrix(
      (1.0, 2.0),
      (1.5, 1.8),
      (2.0, 2.2),
      (8.0, 8.0),
      (8.5, 8.2),
      (9.0, 8.5)
    )

    // Number of clusters
    val k = 2

    // Initial centroids
    var centroids = DenseMatrix(
      (1.0, 2.0),
      (8.0, 8.0)
    )

    var assignments = Array.fill(data.rows)(0)
    var changed = true

    // K-Means iterations
    while (changed) {

      changed = false

      // Assign each point to the nearest centroid
      for (i <- 0 until data.rows) {

        val point = data(i, ::).t

        val distance1 = norm(point - centroids(0, ::).t)
        val distance2 = norm(point - centroids(1, ::).t)

        val newCluster =
          if (distance1 < distance2) 0 else 1

        if (assignments(i) != newCluster) {
          assignments(i) = newCluster
          changed = true
        }
      }

      // Update centroids
      for (cluster <- 0 until k) {

        val clusterPoints =
          (0 until data.rows)
            .filter(i => assignments(i) == cluster)

        if (clusterPoints.nonEmpty) {

          val newCentroid =
            clusterPoints
              .map(i => data(i, ::).t)
              .reduce(_ + _) / clusterPoints.length.toDouble

          centroids(cluster, ::) := newCentroid.t
        }
      }
    }

    // Display results
    println("K-Means Clustering Results:\n")

    for (i <- 0 until data.rows) {
      println(
        s"Point ${i + 1}: (${data(i, 0)}, ${data(i, 1)}) -> Cluster ${assignments(i) + 1}"
      )
    }

    println("\nFinal Centroids:")

    for (i <- 0 until k) {
      println(
        s"Cluster ${i + 1}: (${centroids(i, 0)}, ${centroids(i, 1)})"
      )
    }
  }
}
