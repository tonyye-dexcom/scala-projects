import java.io.{ FileNotFoundException, IOException }

/**
  * Created by ay0716 on 4/4/17.
  */
object ErrorHandling {
  def main(args: Array[String]): Unit = {
    processWithNonFatalError
    process.left.foreach(error => println(s"Terminating process because: ${error.getMessage}"))
  }

  def processWithFatalError: Either[IOException, Int] = {
    process
  }

  def processWithNonFatalError: Unit = {
    process.left.foreach(x => println(s"Non-fatal error: ${x.getMessage}"))
  }

  def process: Either[IOException, Int] = {
    for {
      x <- readData
      y <- writeData(x)
    } yield {
      doSomething
      1
    }
  }

  def readData: Either[IOException, Seq[Int]] = {
    val errorHandling = new ErrorHandling
    for {
      x <- errorHandling.readDbAction
    } yield {
      doSomething
      Seq(1, 2, 3)
    }
  }

  def writeData(data: Seq[Int]): Either[IOException, Int] = {
    val errorHandling = new ErrorHandling
    for {
      x <- errorHandling.writeDbAction
      y <- errorHandling.writeFileAction
    } yield {
      doSomething
      2
    }
  }

  def doSomething: Unit = println("doing something ...")
}

class ErrorHandling {
  def readDbAction: Either[IOException, String] = Left(new IOException("Failed to read from DB"))
  def readFileAction: Either[FileNotFoundException, String] = Left(new FileNotFoundException("Failed to read from file"))
  def writeDbAction: Either[IOException, String] = Left(new IOException("Failed to write to DB"))
  def writeFileAction: Either[FileNotFoundException, String] = Left(new FileNotFoundException("Failed to write to file"))
}
