import com.typesafe.config.ConfigFactory

/**
  * Created by ay0716 on 10/7/16.
  */
object TypesafeConfig {
  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.parseFile(new java.io.File("application.conf"))
    println("Configuration: " +  conf.getString("foo.bar"))
  }
}
