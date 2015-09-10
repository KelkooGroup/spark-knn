package org.apache.spark.mllib.knn

import org.apache.spark.Logging
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.rdd.RDD


class KNN private(val topTreeSize: Int,
                  val topTreeLeafSize: Int
                   ) extends Serializable with Logging {
  def run(data: RDD[Vector]): KNNRDD = {
    val sampled = data.sample(false, topTreeSize / data.count()).collect()
    val topTree = MetricTree.create(sampled, topTreeLeafSize)
    ???
  }
}
