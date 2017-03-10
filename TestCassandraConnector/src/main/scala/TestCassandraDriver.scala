import com.datastax.driver.core.{BatchStatement, BoundStatement, Cluster}

/**
  * Created by ay0716 on 3/10/17.
  */
object TestCassandraDriver {
  def main(args: Array[String]): Unit = {
    val cluster = Cluster.builder()
      .addContactPoint("127.0.0.1")
      .build()
    val session = cluster.connect()
    val batchStatement = new BatchStatement()
    val preparedStatement =
      session.prepare("INSERT INTO dev_data_lake.test_connector (id,  sequence, value) VALUES (?, ?, ?) IF NOT EXISTS")

    for (sequence <- 1 to 10) {
      val seq: java.lang.Integer = sequence
      val boundStatement = new BoundStatement(preparedStatement)
      boundStatement.bind("key1", seq, seq)
      batchStatement.add(boundStatement)
    }
    session.execute(batchStatement)

    batchStatement.clear()
    for (sequence <- 1 to 100) {
      val seq: java.lang.Integer = sequence
      val boundStatement = new BoundStatement(preparedStatement)
      boundStatement.bind("key1", seq, seq)
      batchStatement.add(boundStatement)
    }
    session.execute(batchStatement)

    session.close()
    cluster.close()
  }

}
