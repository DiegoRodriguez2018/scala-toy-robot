import toyrobot.{CommandReader, CommandExecuter}

object Main {
  def main(args: Array[String]): Unit = {   
    val commandsPath = "src/main/scala/resources/input.txt"
    println("\n Welcome to ToyRobot")
    println(s"Input file: ${commandsPath} \n")
    
    if (CommandReader.validate(commandsPath)){
      val commands = CommandReader.getCommands(commandsPath)
      val executer = new CommandExecuter(commands)
      val results = executer.getResults

      val logs = for (
        robot <- results
      ) yield robot.log

      logs.foreach(println)
    } else {
      println(s"Invalid Input from ${commandsPath}. \n Lines must start with one of the following commands: PLACE, MOVE, LEFT, RIGHT, REPORT")
    }
  }
}