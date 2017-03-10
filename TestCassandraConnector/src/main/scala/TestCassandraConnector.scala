import com.datastax.spark.connector.writer.WriteConf
import org.apache.spark.sql.SparkSession

case class TestConnector(id: String, sequence: Integer, value: Integer)

object TestCassandraConnector {
  def main(args: Array[String]): Unit = {

    import com.datastax.spark.connector._

    val TestKeyspace = "dev_data_lake"
    val TestTable = "test_connector"

    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("SparkSessionZipsExample")
      .config("spark.cassandra.connection.host", "127.0.0.1")
      .config("spark.cassandra.connection.port", "9042")
      .getOrCreate()

    val rdd1 = spark.sparkContext.parallelize(for (sequence <- 1 to 100) yield TestConnector("key1", sequence, sequence))

    rdd1.saveToCassandra(TestKeyspace, TestTable, writeConf = WriteConf(ifNotExists = true))
    val rdd2 = spark.sparkContext.parallelize(for (sequence <- 1 to 1000) yield TestConnector("key1", sequence, sequence))
    rdd2.saveToCassandra(TestKeyspace, TestTable, writeConf = WriteConf(ifNotExists = true))
  }
}
