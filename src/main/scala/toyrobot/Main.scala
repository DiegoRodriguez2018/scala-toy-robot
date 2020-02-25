import toyrobot.{CommandReader, ToyRobot}

object Main {
  def main(args: Array[String]): Unit = {   
    println("Welcome to ToyRobot")

    val commandsPath = "src/main/scala/resources/input.txt"
    val commands = CommandReader.getCommands(commandsPath)

    val robot = new ToyRobot(commands)


  }

  def returnString(): String = {
    "random string"
  }
}