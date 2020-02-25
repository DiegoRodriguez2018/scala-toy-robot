import toyrobot.{CommandReader, ToyRobot}

object Main {
  def main(args: Array[String]): Unit = {   
    println()
    println("Welcome to ToyRobot")
    println()

    val commandsPath = "src/main/scala/resources/input.txt"
    val commands = CommandReader.getCommands(commandsPath)

    val robot = new ToyRobot(commands)
    val results = robot.execute

    results.foreach(println)
    val finalPosition = results.last

    println()
    println(s"Robot final position is ${finalPosition}")
  }
}