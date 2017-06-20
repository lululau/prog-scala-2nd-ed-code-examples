// src/main/scala/progscala2/implicits/scala-database-api.scala

package progscala2.implicits {
  package scaladb {
    object implicits {
      import javadb.JRow

      implicit class SRow(jrow: JRow) {
        def get[T](colName: String)(implicit toT: (JRow, String) => T): T =
          toT(jrow, colName)
      }

      implicit val jrowToInt = (jrow: JRow, colName: String) => jrow.getInt(colName)
      implicit val jrowToDouble = (jrow: JRow, colName: String) => jrow.getDouble(colName)
      implicit val jrowToText = (jrow: JRow, colName: String) => jrow.getText(colName)
    }

    object DB {
      def main(args: Array[String]) = {
        import implicits._

        val row = javadb.JRow("one" -> 1, "two" -> 2.2, "three" -> "THREE!")

        val oneValue1: Int = row.get("one")
        val twoValue1: Double = row.get("two")
        val threeValue1: String = row.get("three")

        println(s"one1 -> $oneValue1")
        println(s"two1 -> $twoValue1")
        println(s"three1 -> $threeValue1")

        val oneValue2 = row.get[Int]("one")
        val twoValue2 = row.get[Double]("two")
        val threeValue2 = row.get[String]("three")

        println(s"one2 -> $oneValue2")
        println(s"two2 -> $twoValue2")
        println(s"three2 -> $threeValue2")

      }
    }
  }
}
