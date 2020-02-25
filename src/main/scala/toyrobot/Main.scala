import toyrobot.{CommandReader, ToyRobot}

object Main {
  def main(args: Array[String]): Unit = {   
    println("Welcome to ToyRobot")

    val commandsPath = "src/main/scala/resources/input.txt"
    val commands = CommandReader.getCommands(commandsPath)

    val robot = new ToyRobot(commands)
    val finalPosition = robot.execute

    println(s"REPORT: Robot final position is ${finalPosition}")
  }
}